import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
public class InfixStack {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String exp=br.readLine();
        // code
        Stack<Integer>opnds=new Stack<>();
        Stack<Character>optors=new Stack<>();
        for (int i = 0; i <exp.length(); i++) {
            char ch=exp.charAt(i);
            if(ch=='(')
            {
              optors.push(ch);
            }
            else if(Character.isDigit(ch))
            {
             opnds.push(ch-'0');
            }
            else if(ch==')')
            {
                while(optors.peek()!='(')
                {
                    char optor=optors.pop();
                    int v2=opnds.pop();
                    int v1=opnds.pop();
                    int opv=operation(v1,v2,optor);
                    opnds.push(opv);
                }
                optors.pop();
            }
            else if(ch=='+'||ch=='-'||ch=='*'||ch=='/')
            {
                // ch is wanting higher priority operators to solve first
                while(optors.size()>0&&optors.peek()!='('&&precedence(ch)<=precedence(optors.peek()))
                {
                    char optor=optors.pop();
                    int v2=opnds.pop();
                    int v1=opnds.pop();
                    int opv=operation(v1,v2,optor);
                    opnds.push(opv);
                }
                // ch is pushing itself now
                optors.push(ch);
            }

        }
        while(optors.size()!=0)
        {
            char optor=optors.pop();
            int v2=opnds.pop();
            int v1=opnds.pop();
            int opv=operation(v1,v2,optor);
            opnds.push(opv);
        }
        System.out.println(opnds.peek());
    }
    public static int precedence(char optor)
    {
        if(optor=='+')
        {
            return 1;
        }
        else if(optor=='-')
        {
            return 1;
        }
        else if(optor=='*')
        {
            return 2;
        }
        else{
            return 2;
        }
    }
    public static int operation(int v1,int v2,char optor)
    {
        if(optor=='+')
        {
            return v1+v2;
        }
        else if(optor=='-')
        {
            return v1-v2;
        }
        else if(optor=='*')
        {
            return v1*v2;
        }
        else{
            return v1/v2;
        }
    }

}
