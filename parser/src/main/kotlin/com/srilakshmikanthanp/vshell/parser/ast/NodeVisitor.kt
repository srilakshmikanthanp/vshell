package com.srilakshmikanthanp.vshell.parser.ast

interface NodeVisitor<R> {
  fun visitCommandSubstitution(node: CommandSubstitutionNode): R
  fun visitLeafCommand(node: LeafCommandNode): R
  fun visitCommands(node: CommandsNode): R
  fun visitAndCommand(node: AndCommandNode): R
  fun visitOrCommand(node: OrCommandNode): R
  fun visitPipeCommand(node: PipeCommandNode): R
  fun visitAnd(node: AndNode): R
  fun visitOr(node: OrNode): R
  fun visitPlus(node: PlusNode): R
  fun visitMinus(node: MinusNode): R
  fun visitMultiply(node: MultiplyNode): R
  fun visitDivide(node: DivideNode): R
  fun visitModulo(node: ModuloNode): R
  fun visitPower(node: PowerNode): R
  fun visitNeq(node: NeqNode): R
  fun visitEq(node: EqNode): R
  fun visitGt(node: GtNode): R
  fun visitGte(node: GteNode): R
  fun visitLt(node: LtNode): R
  fun visitLte(node: LteNode): R
  fun visitNot(node: NotNode): R
  fun visitUnaryPlus(node: UnaryPlusNode): R
  fun visitUnaryMinus(node: UnaryMinusNode): R
  fun visitNumeric(node: NumericExpressionValue): R
  fun visitBoolean(node: BooleanExpressionValue): R
  fun visitString(node: StringExpressionValue): R
  fun visitInteger(node: IntegerExpressionValue): R
  fun visitIdentifier(node: IdentifierNode): R
  fun visitTemplateString(node: TemplateStringNode): R
}
