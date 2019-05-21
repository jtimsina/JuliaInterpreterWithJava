package Parse_Tree.Tokens;

public class Lexical_Analyzer {

    // Parent
    private String lexeme;
    private String operator;
    private String literal_integer;
    private char id;

    // Variables
    private String token;
    private String child_digit;

    public Lexical_Analyzer(){}
    public Lexical_Analyzer(String lexeme){
        this.lexeme = lexeme.trim();

        switch (lexeme){
            case Lexemes.assignment_operator:
                token = "assignment_operator";
                break;
            case Lexemes.le_operator:
                token = "le_operator";
                break;
            case Lexemes.lt_operator:
                token = "lt_operator";
                break;
            case Lexemes.ge_operator:
                token = "ge_operator";
                break;
            case Lexemes.gt_operator:
                token = "gt_operator";
                break;
            case Lexemes.eq_operator:
                token = "eq_operator";
                break;
            case Lexemes.ne_operator:
                token = "ne_operator";
                break;
            case Lexemes.add_operator:
                token = "add_operator";
                break;
            case Lexemes.sub_operator:
                token = "sub_operator";
                break;
            case Lexemes.mul_operator:
                token = "mul_operator";
                break;
            case Lexemes.div_operator:
                token = "div_operator";
                break;
            case Lexemes.mod_operator:
                token = "mod_operator";
                break;
            case Lexemes.rev_div_operator:
                token = "rev_div_operator";
                break;
            case Lexemes.exp_operator:
                token = "exp_operator";
                break;
            default:
                token = "nan";
                break;
        }

        if(this.lexeme.length()==1 && is_letter(this.lexeme.charAt(0))){
            id = lexeme.charAt(0);
            token = "id";
        }
        else if(is_number(this.lexeme)){
            literal_integer = this.lexeme;
            token = "literal_integer";
        }
        else
            token = "invalid";
    }

    public String get_type(){
        return token;
    }
    private boolean is_letter(char c){
        if((c>=65 && c<91) || (c>=97 && c<123))
            return true;
        return false;
    }
    private boolean is_digit(char c){
        if(c>=48 && c<58)
            return true;
        return false;
    }
    private boolean is_number(String s){
        for(int i=0; i<s.length(); i++)
            if(!is_digit(s.charAt(i)))
                return false;
        return true;
    }
}
