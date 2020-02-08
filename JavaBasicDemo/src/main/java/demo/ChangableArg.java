package demo;

public class ChangableArg {
    public static class BooleanDemo {

        public static void main(String[] args) {
            boolean result = and(true, true, true);
//            justPrint(true);
//            BooleanUtils.and(true,true);
        }

        private static void justPrint(boolean b) {
            System.out.println(b);
        }

        private static void justPrint(Boolean b) {
            System.out.println(b);
        }

//        private static boolean and(boolean... booleans) {
////            System.out.println("boolean");
//            for (boolean b : booleans) {
//                System.out.println(b);
//            }
//            return true;
//        }

        private static boolean and(Boolean... booleans) {
            System.out.println("boolean");
            for (Boolean b : booleans) {
//                if (!b) {
//                    return false;
//                }
                System.out.println(b);
            }
            return true;
        }
    }
}
