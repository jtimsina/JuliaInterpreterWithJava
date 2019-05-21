package Parse_Tree;

import Parse_Tree.Tokens.Lexical_Analyzer;

public class Node_Program {

    // Parent
    private String program;

    // Children
    private Node_Block child_node_block;

    // Variables
    private boolean syntax_error;
    private String function;
    private String function_id;
    private String block;
    private String end;

    // Constructors
    public Node_Program(){ }
    public Node_Program(String program){
        syntax_error = false;

        // Store program string
        this.program = program.trim();
        String function_statement;

        // Identify children
        function_statement = this.program.substring(0, this.program.indexOf("\n")).trim();
        if(this.program.contains("(") && this.program.contains(")") && this.program.indexOf("(")<this.program.indexOf(")")) {
            function = function_statement.substring(0, function_statement.indexOf(" ")).trim();
            function_id = function_statement.substring(function_statement.indexOf(" "), function_statement.indexOf("(")).trim();
            block = this.program.substring(this.program.indexOf("\n"), Math.max(this.program.lastIndexOf(" "), this.program.lastIndexOf("\n"))).trim();
            end = this.program.substring(Math.max(this.program.lastIndexOf(" "), this.program.lastIndexOf("\n")) + 1).trim();
            syntax_error();
        }
        else
            syntax_error = true;

        // Expansion
        if(!syntax_error)
            expand();
    }

    // Methods

    // Syntax check
    private boolean syntax_error(){
        syntax_error = function_error() || function_id_error() || end_error();
        return syntax_error;
    }
    private boolean function_error(){
        if(function.equals("function"))
            return false;
        else
            return true;
    }
    private boolean function_id_error(){
        Lexical_Analyzer lexical_analyzer = new Lexical_Analyzer(function_id);
        if(lexical_analyzer.get_type().equals("id"))
            return false;
        else
            return true;
    }
    private boolean end_error(){
        if(end.equals("end"))
            return false;
        else
            return true;
    }

    // Create children
    private void expand(){
        child_node_block = new Node_Block(block);
    }

    // Output
    public void display_node(){
        int level = 0;
        // Pre-order traversal
        // Parent
        System.out.printf("Level %d Program node, children:\n", level);
        System.out.printf("\tfunction: \'%s\'\n", function);
        System.out.printf("\tfunction id: \'%s\'\n", function_id);
        System.out.printf("\tblock: \'%s\'\n", block);
        System.out.printf("\tterminal: \'%s\'\n", end);

        // Children
        if(!syntax_error)
            child_node_block.display_node(level);
    }
    public String get_node(){
        StringBuilder strBuilder = new StringBuilder();
        int level = 0;
        // Pre-order traversal
        // Parent
        strBuilder.append("Level "+level+" Program node, children:\n");
        strBuilder.append("\tfunction: \'"+function+"\'\n");
        strBuilder.append("\tfunction id: \'"+function_id+"\'\n");
        strBuilder.append("\tblock: \'"+block+"\'\n");
        strBuilder.append("\tterminal: \'"+end+"\'\n");

        // Children
        if(!syntax_error)
            strBuilder.append(child_node_block.get_node(level));
        return strBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error) {
            if (function_error()) {
                System.out.println("Error in function statement");
                System.out.printf("\tfunction: \'%s\' where it\'s supposed to be \'function'\n", function);
                return true;
            }
            if (function_id_error()) {
                System.out.println("Error in function id");
                System.out.printf("\tfunction id: \'%s\'\n", function_id);
                return true;
            }
            if (end_error()) {
                System.out.println("Error in end statement");
                System.out.printf("\tend: \'%s\'\n", end);
                return true;
            }
            System.out.println("Error in Program");
            return true;
        }
        else
            if(child_node_block.display_error())
                return true;

        return false;
    }
}