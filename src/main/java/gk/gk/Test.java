package gk.gk;

public class Test {
    public static void main(String[] argv){
        UserIdGen userIdGen = new UserIdGen();

        String re = userIdGen.userIdGen(10);
        System.out.println(re);
    }
}
