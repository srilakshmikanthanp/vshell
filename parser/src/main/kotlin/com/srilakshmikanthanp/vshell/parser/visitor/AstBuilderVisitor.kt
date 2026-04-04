package com.srilakshmikanthanp.vshell.parser.visitor

import com.srilakshmikanthanp.vshell.parser.VShellParser
import com.srilakshmikanthanp.vshell.parser.VShellParserBaseVisitor
import com.srilakshmikanthanp.vshell.parser.ast.*

class AstBuilderVisitor: VShellParserBaseVisitor<Node>() {
  override fun visitAdditionNode(ctx: VShellParser.AdditionNodeContext): Node {
    return PlusNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitAndNode(ctx: VShellParser.AndNodeContext): Node {
    return AndNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitTemplateStringExpressionSubstitutionNode(ctx: VShellParser.TemplateStringExpressionSubstitutionNodeContext): Node {
    return this.visit(ctx.expression())
  }

  override fun visitCommandSubstitutionNode(ctx: VShellParser.CommandSubstitutionNodeContext): Node {
    return CommandSubstitutionNode(this.visit(ctx.command()).asCommandNode())
  }

  override fun visitTemplateStringLiteralTextNode(ctx: VShellParser.TemplateStringLiteralTextNodeContext): Node {
    return StringExpressionValue(decode(ctx.text))
  }

  override fun visitTemplateStringLiteral(ctx: VShellParser.TemplateStringLiteralContext): Node {
    val segments = ctx.segments.map { this.visit(it).asVShellExpressionNode() }
    return TemplateStringNode(segments)
  }

  override fun visitBinaryIntegerLiteralNode(ctx: VShellParser.BinaryIntegerLiteralNodeContext): Node {
    return IntegerExpressionValue(ctx.text.substring(2).toLong(2))
  }

  override fun visitDivisionNode(ctx: VShellParser.DivisionNodeContext): Node {
    return DivideNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitDoubleQuotedStringLiteralNode(ctx: VShellParser.DoubleQuotedStringLiteralNodeContext): Node {
    return StringExpressionValue(decode(ctx.text.substring(1, ctx.text.length - 1)))
  }

  override fun visitEqualsNode(ctx: VShellParser.EqualsNodeContext): Node {
    return EqNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitExponentiationNode(ctx: VShellParser.ExponentiationNodeContext): Node {
    return PowerNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitFalseLiteralNode(ctx: VShellParser.FalseLiteralNodeContext): Node {
    return BooleanExpressionValue(false)
  }

  override fun visitGreaterThanNode(ctx: VShellParser.GreaterThanNodeContext): Node {
    return GtNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitGreaterThanOrEqualNode(ctx: VShellParser.GreaterThanOrEqualNodeContext): Node {
    return GteNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitHexIntegerLiteralNode(ctx: VShellParser.HexIntegerLiteralNodeContext): Node {
    return IntegerExpressionValue(ctx.text.substring(2).toLong(16))
  }

  override fun visitLessThanNode(ctx: VShellParser.LessThanNodeContext): Node {
    return LtNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitLessThanOrEqualNode(ctx: VShellParser.LessThanOrEqualNodeContext): Node {
    return LteNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitModuloNode(ctx: VShellParser.ModuloNodeContext): Node {
    return ModuloNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitMultiplicationNode(ctx: VShellParser.MultiplicationNodeContext): Node {
    return MultiplyNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitIdentifierNode(ctx: VShellParser.IdentifierNodeContext): Node {
    return IdentifierNode(ctx.text)
  }

  override fun visitNotEqualsNode(ctx: VShellParser.NotEqualsNodeContext): Node {
    return NeqNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitSubExpressionNode(ctx: VShellParser.SubExpressionNodeContext): Node {
    return this.visit(ctx.expression())
  }

  override fun visitNotNode(ctx: VShellParser.NotNodeContext): Node {
    return NotNode(this.visit(ctx.operand).asVShellExpressionNode())
  }

  override fun visitOctalIntegerLiteralNode(ctx: VShellParser.OctalIntegerLiteralNodeContext): Node {
    return IntegerExpressionValue(ctx.text.substring(2).toLong(8))
  }

  override fun visitOrNode(ctx: VShellParser.OrNodeContext): Node {
    return OrNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitSingleQuotedStringLiteralNode(ctx: VShellParser.SingleQuotedStringLiteralNodeContext): Node {
    return StringExpressionValue(decode(ctx.text.substring(1, ctx.text.length - 1)))
  }

  override fun visitSubtractionNode(ctx: VShellParser.SubtractionNodeContext): Node {
    return MinusNode(this.visit(ctx.left).asVShellExpressionNode(), this.visit(ctx.right).asVShellExpressionNode())
  }

  override fun visitTrueLiteralNode(ctx: VShellParser.TrueLiteralNodeContext): Node {
    return BooleanExpressionValue(true)
  }

  override fun visitUnaryMinusNode(ctx: VShellParser.UnaryMinusNodeContext): Node {
    return UnaryMinusNode(this.visit(ctx.operand).asVShellExpressionNode())
  }

  override fun visitUnaryPlusNode(ctx: VShellParser.UnaryPlusNodeContext): Node {
    return UnaryPlusNode(this.visit(ctx.operand).asVShellExpressionNode())
  }

  override fun visitNumericLiteralNode(ctx: VShellParser.NumericLiteralNodeContext): Node {
    return NumericExpressionValue(ctx.text.toDouble())
  }

  override fun visitSubCommandNode(ctx: VShellParser.SubCommandNodeContext): Node {
    return this.visit(ctx.command())
  }

  override fun visitIntegerLiteralNode(ctx: VShellParser.IntegerLiteralNodeContext): Node {
    return IntegerExpressionValue(ctx.text.toLong())
  }

  override fun visitAndCommandNode(ctx: VShellParser.AndCommandNodeContext): Node {
    return this.visit(ctx.left).asCommandNode().and(this.visit(ctx.right).asCommandNode())
  }

  override fun visitOrCommandNode(ctx: VShellParser.OrCommandNodeContext): Node {
    return this.visit(ctx.left).asCommandNode().or(this.visit(ctx.right).asCommandNode())
  }

  override fun visitPipeCommandNode(ctx: VShellParser.PipeCommandNodeContext): Node {
    return this.visit(ctx.left).asCommandNode().pipe(this.visit(ctx.right).asCommandNode())
  }

  override fun visitCommandNode(ctx: VShellParser.CommandNodeContext): Node {
    return LeafCommandNode(IdentifierNode(ctx.IDENTIFIER().text), ctx.arguments.map { this.visit(it) })
  }

  override fun visitExpressionPrimaryNode(ctx: VShellParser.ExpressionPrimaryNodeContext): Node {
    return this.visit(ctx.primary())
  }

  override fun visitTemplateStringLiteralNode(ctx: VShellParser.TemplateStringLiteralNodeContext): Node {
    return this.visit(ctx.templateStringLiteral())
  }

  override fun visitCommandsNode(ctx: VShellParser.CommandsNodeContext): Node {
    return CommandsNode(ctx.items.map { this.visit(it).asCommandNode() })
  }

  private fun decode(str: String): String {
    return str.replace("\\\\", "\\")
  }
}
