import org.junit.Test;

public class Main
{
    public static void main(String[] args)
    {
      /*  Scanner in=new Scanner(System.in);
        String s=in.nextLine();
        String t=in.nextLine();
        int sl=s.length();
        String replace = s.replace(t, "");
        int tl= replace.length();
        int o=(sl-tl)/t.length();
        System.out.println(o);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));*/

    }

    @Test
    public void testMaster()
    {

        System.out.println("提交到master分支。。。");

        System.out.println("切换到dev....");

        System.out.println("master创建并切换到bug...");

    }

    @Test
    public void testRebase()
    {
        System.out.println("checkout dev...");
    }

}
