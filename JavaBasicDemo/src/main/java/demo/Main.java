package demo;

import java.lang.*;
import java.util.*;

public class Main{
    public static void main2(String[] s){
        Object object;

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for(int i=1; i<=n; i++){
            int state = 0;
            String in = null;
            while((in = sc.nextLine()).length()==0);
            if (in.length()<=2){
                System.out.println(in);
            }

            boolean cant_double = false;
            boolean cant_trible = false;
            StringBuilder sb = new StringBuilder();
            sb.append(in.charAt(0));
            for(int idx=1; idx<in.length(); idx++){

                switch (state){
                    case 0:

                        if (in.charAt(idx)==in.charAt(idx-1)){
                            state=1;
                        }
                        sb.append(in.charAt(idx));
                        break;
                    case 1:
                        if (in.charAt(idx)==in.charAt(idx-1)){
                            continue;
                        }else{
                            sb.append(in.charAt(idx));
                            state=2;
                        }
                        break;
                    case 2:
                        if (in.charAt(idx)==in.charAt(idx-1)){
                            continue;
                        }else{
                            sb.append(in.charAt(idx));
                            state=0;
                        }
                }
            }

            System.out.println(sb.toString());
        }

    }

    public static void main3(String[] s){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int t=0; t<n; t++){
            int result = 0;
            int N = sc.nextInt();
            int[] arr = new int[N];
            int[] dp = new int[N];

            for(int idx=0; idx<dp.length; idx++)
                dp[idx]=1;




            for(int i=0; i<N; i++)
                arr[i] = sc.nextInt();
            dp[0] = arr[dp.length-1]<arr[0]?dp[dp.length-1]+1:dp[0];
            for(int idx=1; idx<dp.length; idx++){
                if(arr[idx]>arr[idx-1]){
                    dp[idx]=dp[idx-1]+1;
                }
            }

            dp[dp.length-1] = arr[dp.length-1]>arr[0]?dp[0]+1:dp[dp.length-1];
            for(int idx=dp.length-2; idx>=0; idx--){
                if(arr[idx]>arr[idx+1]){
                    dp[idx]=dp[idx+1]+1;
                }
            }

            for(Integer i:dp){
                result += i;
            }
            System.out.println(result);
        }
    }

    public static void main4(String[] s){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arrl = new int[n];
        for(int idx=0; idx<n; idx++){
            arrl[idx] = sc.nextInt();
        }
        int min = arrl[0];
        int max = arrl[0];
        for(Integer i:arrl){
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        double l=0.0;
        double r=max*1.0;
        double result=0.0;
        double mid = l+(r-l)/2;
        while(l<r){
            mid = l+(r-l)/2;
            int sum = 0;
            for(Integer i:arrl){
                sum+=i/mid;
            }
//            System.out.print(mid+" ");
//            System.out.println(sum);
            if(sum<m){
                //不符合
                r=mid;
            }else{
                //可能符合
                l=mid+1;
                result = Math.max(result, mid);
            }

        }
        System.out.printf("%.2f",result);

    }

    public static void increase(Integer i){
        i+=1;
        return;
    }
    static class A{
        final Integer a;
        {
            a = 2555;
        }
    }


    public static void main(String[] args) {
        A a = new A();
        A b = new A();
        System.out.println(a.a==b.a);

    }
}