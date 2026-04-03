parser grammar VShellParser;

options {
    tokenVocab = VShellLexer;
}

commands
    : (items += command (SEMICOLON items += command)* SEMICOLON?)? EOF #CommandsNode;

command:
	LPARENTHESES command RPARENTHESES #SubCommandNode
	| left = command PIPE right = command #PipeCommandNode
	| left = command AND right = command #AndCommandNode
	| left = command OR right = command	#OrCommandNode
	| name = IDENTIFIER (arguments += primary)* #CommandNode;

expression
	// Parentheses for grouping
	: LPARENTHESES expression RPARENTHESES #SubExpressionNode

	// Unary operators
	| PLUS operand = expression		#UnaryPlusNode
	| MINUS operand = expression	#UnaryMinusNode
	| NOT operand = expression		#NotNode

	// Arithmetic operators
	| <assoc = right> left = expression EXPONENTIATION right = expression	#ExponentiationNode
	| left = expression MULTIPLY right = expression							#MultiplicationNode
	| left = expression DIVIDE right = expression							#DivisionNode
	| left = expression MODULO right = expression							#ModuloNode
	| left = expression PLUS right = expression								#AdditionNode
	| left = expression MINUS right = expression							#SubtractionNode

	// Relational operators
	| left = expression LESS_THAN right = expression				#LessThanNode
	| left = expression GREATER_THAN right = expression				#GreaterThanNode
	| left = expression LESS_THAN_OR_EQUAL right = expression		#LessThanOrEqualNode
	| left = expression GREATER_THAN_OR_EQUAL right = expression	#GreaterThanOrEqualNode

	// Equality operators
	| left = expression EQUALS right = expression		#EqualsNode
	| left = expression NOT_EQUALS right = expression	#NotEqualsNode

	// Logical operators
	| left = expression AND right = expression	#AndNode
	| left = expression OR right = expression	#OrNode

    // Command substitution inside expression
    | COMMAND_SUBSTITUTION_START command RPARENTHESES #CommandSubstitutionNode

	// Identifiers
	| IDENTIFIER #IdentifierNode

	// Literals
	| primary #ExpressionPrimaryNode;

templateStringSegment
	: TEMPLATE_STRING_LITERAL_TEXT                      #TemplateStringLiteralTextNode
	| EXPRESSION_SUBSTITUTION_START expression RBRACE   #TemplateStringExpressionSubstitutionNode;

templateStringLiteral
	: TEMPLATE_STRING_START (segments += templateStringSegment)+ TEMPLATE_STRING_END;

primary
	: OCTAL_INTEGER_LITERAL				#OctalIntegerLiteralNode
	| INTEGER_LITERAL					#IntegerLiteralNode
	| HEX_INTEGER_LITERAL				#HexIntegerLiteralNode
	| BINARY_INTEGER_LITERAL			#BinaryIntegerLiteralNode
	| NUMERIC_LITERAL					#NumericLiteralNode
	| SINGLE_QUOTED_STRING_LITERAL		#SingleQuotedStringLiteralNode
	| DOUBLE_QUOTED_STRING_LITERAL		#DoubleQuotedStringLiteralNode
	| TRUE								#TrueLiteralNode
	| FALSE								#FalseLiteralNode
	| templateStringLiteral		        #TemplateStringLiteralNode;
