package Parse_Tree.Statements;

import Parse_Tree.Node_Iter;
import Parse_Tree.Tokens.Lexemes;
import Parse_Tree.Node_Block;
import Parse_Tree.Tokens.Lexical_Analyzer;

public class Node_For_Statement {

    // Parent
    private String for_statement;

    // Children
    private Node_Block child_node_block;
    private Node_Iter child_node_iter;

    // Variables
    private String child_for;
    private String child_id;
    private String child_block;
    private String child_equal;
    private String child_iter;
    private String child_end;
    private boolean syntax_error;

    public Node_For_Statement(){}
    public Node_For_Statement(String for_statement){
        syntax_error = false;
        this.for_statement = for_statement.trim();

        // Identify Children
        if(!for_error()){
            child_for = this.for_statement.substring(0, this.for_statement.indexOf(" ")).trim();
            child_id = this.for_statement.substring(this.for_statement.indexOf(" "), this.for_statement.indexOf("=")).trim();
            child_equal = Lexemes.assignment_operator;
            child_iter = this.for_statement.substring(this.for_statement.indexOf(" "), this.for_statement.indexOf("\n")).trim();
            child_block = this.for_statement.substring(this.for_statement.indexOf("\n"), this.for_statement.length()-3).trim();
            child_end = this.for_statement.substring(this.for_statement.length()-3).trim();
            syntax_error();
        }
        else
            syntax_error = true;

        if(!syntax_error)
            expand();
    }

    private boolean syntax_error(){
        syntax_error = id_error() || iter_error() || block_error() || end_error();
        return syntax_error;
    }
    private boolean for_error() {
        if (!this.for_statement.contains("for") || !this.for_statement.contains(Lexemes.assignment_operator))
            return true;
        return false;
    }
    private boolean id_error(){
        Lexical_Analyzer id_analyzer = new Lexical_Analyzer(child_id);
        if(id_analyzer.get_type().equals("id"))
            return true;
        return false;
    }
    private boolean iter_error(){
        return false;
    }
    private boolean block_error() { return false; }
    private boolean end_error() {
        if(!child_end.equals("end"))
            return true;
        return false;
    }

    // Create children
    private void expand(){
        child_node_block = new Node_Block(child_block);
        child_node_iter = new Node_Iter(child_iter);
    }

    // Output
    public void display_node(int level){
        level++;
        for(int i=0; i<level; i++)
            System.out.print("- ");
        // Pre-order traversal
        // Parent
        System.out.printf("Level %d For Statement node, children:\n", level);
        System.out.printf("\tloop function: \'%s\'\n", child_for);
        System.out.printf("\tid: \'%s\'\n", child_id);
        System.out.printf("\tassignment operator: \'%s\'\n", child_equal);
        System.out.printf("\titer: \'%s\'\n", child_iter);
        System.out.printf("\tblock: \'%s\'\n", child_block);
        System.out.printf("\tterminal: \'%s\'\n", child_end);

        // Children
        if(!syntax_error) {
            child_node_iter.display_node(level);
            child_node_block.display_node(level);
        }
    }
    public String get_node(int level){
        StringBuilder stringBuilder = new StringBuilder();
        level++;
        for(int i=0; i<level; i++)
           stringBuilder.append("- ");
        // Pre-order traversal
        // Parent
        stringBuilder.append("Level "+level+" For Statement node, children:\n");
        stringBuilder.append("\tloop function: \'"+child_for+"\'\n");
        stringBuilder.append("\tid: \'"+child_id+"\'\n");
        stringBuilder.append("\tassignment operator: \'"+child_equal+"\'\n");
        stringBuilder.append("\titer: \'"+child_iter+"\'\n");
        stringBuilder.append("\tblock: \'"+child_block+"\'\n");
        stringBuilder.append("\tterminal: \'"+child_end+"\'\n");

        // Children
        if(!syntax_error) {
            stringBuilder.append(child_node_iter.get_node(level));
            stringBuilder.append(child_node_block.get_node(level));
        }
        return stringBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error){
            System.out.println("Error in For Statement");
            if(for_error()) {
                System.out.printf("\tfor function: \'%s\' where it\'s supposed to be \'for'\n", child_for);
                return true;
            }
            if(id_error()){
                System.out.printf("\terror on id: \'%s\'\n", child_id);
                return true;
            }
            if(end_error()){
                System.out.printf("\tterminal: \'%s\' where it\'s supposed to be \'end'\n", child_end);
                return true;
            }
            return true;
        }
        else {
            if(child_node_iter.display_error())
                return true;
            if(child_node_block.display_error())
                return true;
        }
        return false;
    }
}
