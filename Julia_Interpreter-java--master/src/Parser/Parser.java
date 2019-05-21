package Parser;

import Parse_Tree.Node_Program;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    // Objects
    private Node_Program program_node;
    private String inputFile;
    private String program;

    // Constructor
    public Parser(String inputFile){
        this.inputFile = inputFile;
    }

    // Methods
    public void parse(){
        try{ program = new String(Files.readAllBytes(Paths.get(inputFile)));}
        catch (IOException e){ System.out.println(e.toString()); }
    }
    public void build_parse_tree(){
        program_node = new Node_Program(program);
    }
    public void print_parsed_program(){
        System.out.println("#######################");
        System.out.printf("The program \"%s\" parsed is:\n", inputFile);
        System.out.println(program);
        System.out.println("#######################");
        System.out.print("*#*#Parse Tree#*#*");
        program_node.display_node();
        System.out.print("*#*#End of the Parse Tree#*#*");
    }
    public void print_parsed_program_to_file(String output_file){
        String output_string = "#######################\n" +
                "The program \""+ inputFile +"\" parsed is:\n" +
                program +
                "\n#######################\n" +
                "*#*#Parse Tree#*#*\n" +
                program_node.get_node()+
                "*#*#End of the Parse Tree#*#*";
        try (PrintWriter out = new PrintWriter(output_file)) {
            out.println(output_string);
            System.out.printf("Parse Tree written to file: %s", output_file);
        } catch (IOException e){ System.out.println(e.toString()); }
    }
    public boolean errors(){
        return program_node.display_error();
    }
}
