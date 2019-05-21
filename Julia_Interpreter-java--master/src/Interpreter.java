import Parser.Parser;

public class Interpreter {
    public static void main(String[] args) {

        // Objects
        String filename;
        String extension;
        String input_path, output_path;
        Parser parser_instance;

        // Object initialization
        filename = "program1"; // Julia program to parse
        extension = ".txt"; // extension
        input_path = "src/Input/";  // input directory
        output_path = "src/Output/";    // output directory

        parser_instance = new Parser(input_path+filename+extension);
        parser_instance.parse();
        parser_instance.build_parse_tree();

        if(!parser_instance.errors()) {
            System.out.printf("No errors found in %s, the parser will attempt to generate the Parse Tree ...\n", filename);
//            parser_instance.print_parsed_program();   // Display Parse Tree in Standard Output
            parser_instance.print_parsed_program_to_file(output_path+filename+extension); // Print Parse Tree to file
        }
    }
}
