package Parse_Tree.Expressions;

import Parse_Tree.Tokens.Lexical_Analyzer;

public class Node_Arithmetic_Expression {

    // Parent
    private String arithmetic_expression;

    // Children
    private Node_Binary_Expression child_node_binary_expression;

    // Variables
    private String child_id;
    private String child_literal_integer;
    private String child_binary_expression;
    private boolean syntax_error;

    public Node_Arithmetic_Expression(){}
    public Node_Arithmetic_Expression(String arithmetic_expression){
        syntax_error=false;
        this.arithmetic_expression = arithmetic_expression.trim();

        // identify children
        Lexical_Analyzer arithmetic_expression_analyzer = new Lexical_Analyzer(this.arithmetic_expression);
        switch (arithmetic_expression_analyzer.get_type()){
            case "id":
                child_id = this.arithmetic_expression;
                break;
            case "literal_integer":
                child_literal_integer = this.arithmetic_expression;
                break;
            case "invalid":
                child_binary_expression = this.arithmetic_expression;
                expand();
                break;
            default:
                syntax_error=true;
                break;
        }
    }

    private boolean syntax_error(){
        syntax_error = id_error() || literal_integer_error() || binary_expression_error();
        return syntax_error;
    }
    private boolean id_error(){
        Lexical_Analyzer id_analyzer = new Lexical_Analyzer(child_id);
        if(!id_analyzer.get_type().equals("id"))
            return true;
        return false;
    }
    private boolean literal_integer_error(){
        Lexical_Analyzer literal_integer_analyzer = new Lexical_Analyzer(child_literal_integer);
        if(!literal_integer_analyzer.get_type().equals("literal_integer"))
            return true;
        return false;
    }
    private boolean binary_expression_error(){
        return false;
    }


    // Create children
    private void expand(){
        child_node_binary_expression = new Node_Binary_Expression(child_binary_expression);
    }

    // Output
    public void display_node(int level){
        level++;
        for(int i=0; i<level; i++)
            System.out.print("- ");
        // Pre-order traversal
        // Parent
        System.out.printf("Level %d Arithmetic Expression node, child:\n", level);
        if(child_id!=null)
            System.out.printf("\tid: \'%s\'\n", child_id);
        if(child_literal_integer!=null)
            System.out.printf("\tliteral integer: \'%s\'\n", child_literal_integer);
        if(child_binary_expression!=null)
            System.out.printf("\tbinary expression: \'%s\'\n", child_binary_expression);

        // Children
        if(!syntax_error) {
            if(child_node_binary_expression!=null)
                child_node_binary_expression.display_node(level);
        }
    }
    public String get_node(int level){
        StringBuilder stringBuilder = new StringBuilder();
        level++;
        for(int i=0; i<level; i++)
            stringBuilder.append("- ");
        // Pre-order traversal
        // Parent
        stringBuilder.append("Level "+level+" Arithmetic Expression node, child:\n");
        if(child_id!=null)
            stringBuilder.append("\tid: \'"+child_id+"\'\n");
        if(child_literal_integer!=null)
            stringBuilder.append("\tliteral integer: \'"+child_literal_integer+"\'\n");
        if(child_binary_expression!=null)
            stringBuilder.append("\tbinary expression: \'"+child_binary_expression+"\'\n");

        // Children
        if(!syntax_error) {
            if(child_node_binary_expression!=null)
                stringBuilder.append(child_node_binary_expression.get_node(level));
        }
        return stringBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error){
            System.out.println("Error Arithmetic Expression");
            if(id_error()){
                System.out.printf("\terror on id: \'%s\'\n", child_id);
                return true;
            }
            if(literal_integer_error()) {
                System.out.printf("\terror on literal integer: \'%s\'\n", child_literal_integer);
                return true;
            }
            return true;
        }
        else {
            if(child_node_binary_expression!=null)
                if(child_node_binary_expression.display_error())
                    return true;
        }
        return false;
    }

}
