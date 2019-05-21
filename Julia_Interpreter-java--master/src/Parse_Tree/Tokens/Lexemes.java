package Parse_Tree.Tokens;

/*
*
*
Lexical Analyzer
id → letter
literal_integer → digit literal_integer | digit

assignment_operator → =
le_operator → <=
lt_operator → <
ge_operator → >=
gt_operator → >
eq_operator → = =
ne_operator → !=
add_operator → +
sub_operator → -
mul_operator → *
div_operator → /
mod_operator → %
rev_div_operator → \
exp_operator → ^
*
* */

public interface Lexemes {

    String assignment_operator = "=";

    String le_operator = "<=";
    String lt_operator = "<";
    String ge_operator = ">=";
    String gt_operator = ">";
    String eq_operator = "==";
    String ne_operator = "!=";

    String add_operator = "+";
    String sub_operator = "-";
    String mul_operator = "*";
    String div_operator = "/";
    String mod_operator = "%";
    String rev_div_operator = "\\";
    String exp_operator = "^";

}
