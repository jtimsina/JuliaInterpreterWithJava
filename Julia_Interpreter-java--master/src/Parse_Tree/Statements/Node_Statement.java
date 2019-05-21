package Parse_Tree.Statements;

public class Node_Statement {

    // Parent
    private String statement;

    // Children
    private Node_Assignment_Statement child_node_assignment_statement;
    private Node_If_Statement child_node_if_statement;
    private Node_While_Statement child_node_while_statement;
    private Node_For_Statement child_node_for_statement;
    private Node_Print_Statement child_node_print_statement;

    // Variables
    private String child_type;
    private boolean syntax_error;
    private String if_statement;
    private String assignment_statement;
    private String while_statement;
    private String print_statement;
    private String for_statement;

    public Node_Statement(){}
    public Node_Statement(String statement){
        syntax_error = false;
        // Store block string
        this.statement = statement.trim();

        // identify children
        if(this.statement.contains("if")) {
            if_statement = this.statement;
            child_type = "if";
            expand(if_statement);
        }
        else if(this.statement.contains("while") && this.statement.contains(" end ")){
            while_statement = this.statement;
            child_type = "while";
            expand(while_statement);
        }
        else if(this.statement.contains("for") && this.statement.contains(" end ")){
            for_statement = this.statement;
            child_type = "for";
            expand(for_statement);
        }
        else if(this.statement.contains("print")){
            print_statement = this.statement;
            child_type = "print";
            expand(print_statement);
        }
        else if(this.statement.contains(" = ")){
            assignment_statement = this.statement;
            child_type = "assign";
            expand(assignment_statement);
        }
        else
            syntax_error=true;
    }

    // Methods

    // Syntax check
    private boolean syntax_error(){
        return syntax_error;
    }
    private boolean statement_error(){return false;}

    // Create children
    private void expand(String expansion){
        if(child_type == null)
            return;
        else
        switch (child_type){
            case "if":
                child_node_if_statement = new Node_If_Statement(if_statement);
                break;
            case "while":
                child_node_while_statement = new Node_While_Statement(while_statement);
                break;
            case "for":
                child_node_for_statement = new Node_For_Statement(for_statement);
                break;
            case "print":
                child_node_print_statement = new Node_Print_Statement(print_statement);
                break;
            case "assign":
                child_node_assignment_statement = new Node_Assignment_Statement(assignment_statement);
                break;
            default:
                break;
        }
    }

    // Output
    public void display_node(int level){
        if(!syntax_error) {
            level++;
            for (int i = 0; i < level; i++)
                System.out.print("- ");
            // Pre-order traversal
            System.out.printf("Level %d ", level);
            switch (child_type) {
                case "if":
                    // Parent
                    System.out.println("Statement node, child:\n");
                    System.out.printf("\t If Statement: \'%s\'\n", if_statement);
                    // Child
                    child_node_if_statement.display_node(level);
                    break;
                case "while":
                    // Parent
                    System.out.println("Statement node, child:\n");
                    System.out.printf("\tWhile Statement: \'%s\'\n", while_statement);
                    // Child
                    child_node_while_statement.display_node(level);
                    break;
                case "for":
                    // Parent
                    System.out.println("Statement node, child:\n");
                    System.out.printf("\tFor Statement: \'%s\'\n", for_statement);
                    // Child
                    child_node_for_statement.display_node(level);
                    break;
                case "print":
                    // Parent
                    System.out.println("Statement node, child:\n");
                    System.out.printf("\tPrint Statement: \'%s\'\n", print_statement);
                    // Child
                    child_node_print_statement.display_node(level);
                    break;
                case "assign":
                    // Parent
                    System.out.println("Statement node, child:\n");
                    System.out.printf("\tAssign statement: \'%s\'\n", assignment_statement);
                    // Child
                    child_node_assignment_statement.display_node(level);
                    break;
                default:
                    break;
            }
        }
    }
    public String get_node(int level){
        StringBuilder stringBuilder = new StringBuilder();
        if(!syntax_error) {
            level++;
            for (int i = 0; i < level; i++)
                stringBuilder.append("- ");
            // Pre-order traversal
            stringBuilder.append(("Level "+level+" "));
            switch (child_type) {
                case "if":
                    // Parent
                    stringBuilder.append("Statement node, child:\n");
                    stringBuilder.append("\t If Statement: \'"+if_statement+"\'\n");
                    // Child
                    stringBuilder.append(child_node_if_statement.get_node(level));
                    break;
                case "while":
                    // Parent
                    stringBuilder.append("Statement node, child:\n");
                    stringBuilder.append("\tWhile Statement: \'"+while_statement+"\'\n");
                    // Child
                    stringBuilder.append(child_node_while_statement.get_node(level));
                    break;
                case "for":
                    // Parent
                    stringBuilder.append("Statement node, child:\n");
                    stringBuilder.append("\tFor Statement: \'"+for_statement+"\'\n");
                    // Child
                    stringBuilder.append(child_node_for_statement.get_node(level));
                    break;
                case "print":
                    // Parent
                    stringBuilder.append("Statement node, child:\n");
                    stringBuilder.append("\tPrint Statement: \'"+print_statement+"\'\n");
                    // Child
                    stringBuilder.append(child_node_print_statement.get_node(level));
                    break;
                case "assign":
                    // Parent
                    stringBuilder.append("Statement node, child:\n");
                    stringBuilder.append("\tAssign statement: \'"+assignment_statement+"\'\n");
                    // Child
                    stringBuilder.append(child_node_assignment_statement.get_node(level));
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error){
            System.out.printf("Error in statement: %s\n", statement);
            return true;
        }
        else
            switch (child_type) {
                case "if":
                    if(child_node_if_statement.display_error())
                        return true;
                    break;
                case "while":
                    if(child_node_while_statement.display_error())
                        return true;
                    break;
                case "for":
                    if(child_node_for_statement.display_error())
                        return true;
                    break;
                case "print":
                    if(child_node_print_statement.display_error())
                        return true;
                    break;
                case "assign":
                    if(child_node_assignment_statement.display_error())
                        return true;
                    break;
                default:
                    break;
            }
        return false;
    }
}
