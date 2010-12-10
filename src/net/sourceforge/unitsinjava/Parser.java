//=========================================================================
//
//  This file was generated by Mouse 1.3 at 2010-11-02 16:19:27 GMT\nfrom
//    grammar 'D:\Units\ units\grammar.peg'.
//
//=========================================================================

package net.sourceforge.unitsinjava;

import net.sourceforge.unitsinjava.Source;

public class Parser extends net.sourceforge.unitsinjava.ParserBase
{
  final Semantics sem;
  
  //=======================================================================
  //
  //  Initialization
  //
  //=======================================================================
  //-------------------------------------------------------------------
  //  Constructor
  //-------------------------------------------------------------------
  public Parser()
    {
      sem = new Semantics();
      sem.rule = this;
      super.sem = sem;
    }
  
  //-------------------------------------------------------------------
  //  Run the parser
  //-------------------------------------------------------------------
  public boolean parse(Source src)
    {
      super.init(src);
      sem.init();
      if (unitexpr()) return true;
      return failure();
    }
  
  //-------------------------------------------------------------------
  //  Get semantics
  //-------------------------------------------------------------------
  public Semantics semantics()
    { return sem; }
  
  //=======================================================================
  //
  //  Parsing procedures
  //
  //=======================================================================
  //=====================================================================
  //  unitexpr = space expr? EOT {unitexpr} ~{error} ;
  //=====================================================================
  boolean unitexpr()
    {
      begin("unitexpr");
      if (unitexpr_0()) {sem.unitexpr(); return accept();} else sem.error();
      return reject();
    }
  
  //-------------------------------------------------------------------
  //  unitexpr_0 = space expr? EOT
  //-------------------------------------------------------------------
  boolean unitexpr_0()
    {
      begin("");
      space();
      expr();
      if (!EOT()) return rejectInner();
      return acceptInner();
    }
  
  //=====================================================================
  //  expr = term ((PLUS / MINUS) term)* {expr} / (SLASH / PER) product
  //    {inverse} ;
  //=====================================================================
  boolean expr()
    {
      begin("expr");
      if (expr_0()) {sem.expr(); return accept();}
      if (expr_1()) {sem.inverse(); return accept();}
      return reject();
    }
  
  //-------------------------------------------------------------------
  //  expr_0 = term ((PLUS / MINUS) term)*
  //-------------------------------------------------------------------
  boolean expr_0()
    {
      begin("");
      if (!term()) return rejectInner();
      while (expr_2());
      return acceptInner();
    }
  
  //-------------------------------------------------------------------
  //  expr_1 = (SLASH / PER) product
  //-------------------------------------------------------------------
  boolean expr_1()
    {
      begin("");
      if (!SLASH()
       && !PER()
         ) return rejectInner();
      if (!product()) return rejectInner();
      return acceptInner();
    }
  
  //-------------------------------------------------------------------
  //  expr_2 = (PLUS / MINUS) term
  //-------------------------------------------------------------------
  boolean expr_2()
    {
      begin("");
      if (!PLUS()
       && !MINUS()
         ) return rejectInner();
      if (!term()) return rejectInner();
      return acceptInner();
    }
  
  //=====================================================================
  //  term = product ((STAR / SLASH / PER) product)* {term} ;
  //=====================================================================
  boolean term()
    {
      begin("term");
      if (!product()) return reject();
      while (term_0());
      sem.term();
      return accept();
    }
  
  //-------------------------------------------------------------------
  //  term_0 = (STAR / SLASH / PER) product
  //-------------------------------------------------------------------
  boolean term_0()
    {
      begin("");
      if (!STAR()
       && !SLASH()
       && !PER()
         ) return rejectInner();
      if (!product()) return rejectInner();
      return acceptInner();
    }
  
  //=====================================================================
  //  product = factor (![+-] factor)* {product} ;
  //=====================================================================
  boolean product()
    {
      begin("product");
      if (!factor()) return reject();
      while (product_0());
      sem.product();
      return accept();
    }
  
  //-------------------------------------------------------------------
  //  product_0 = ![+-] factor
  //-------------------------------------------------------------------
  boolean product_0()
    {
      begin("");
      if (!aheadNotIn("+-")) return rejectInner();
      if (!factor()) return rejectInner();
      return acceptInner();
    }
  
  //=====================================================================
  //  factor = unary ((HAT / STARSTAR) unary)* {factor} ;
  //=====================================================================
  boolean factor()
    {
      begin("factor");
      if (!unary()) return reject();
      while (factor_0());
      sem.factor();
      return accept();
    }
  
  //-------------------------------------------------------------------
  //  factor_0 = (HAT / STARSTAR) unary
  //-------------------------------------------------------------------
  boolean factor_0()
    {
      begin("");
      if (!HAT()
       && !STARSTAR()
         ) return rejectInner();
      if (!unary()) return rejectInner();
      return acceptInner();
    }
  
  //=====================================================================
  //  unary = (PLUS / MINUS)? primary {unary} ;
  //=====================================================================
  boolean unary()
    {
      begin("unary");
      unary_0();
      if (!primary()) return reject();
      sem.unary();
      return accept();
    }
  
  //-------------------------------------------------------------------
  //  unary_0 = PLUS / MINUS
  //-------------------------------------------------------------------
  boolean unary_0()
    {
      begin("");
      if (PLUS()) return acceptInner();
      if (MINUS()) return acceptInner();
      return rejectInner();
    }
  
  //=====================================================================
  //  primary = numexpr {makeNumUnit} / LPAR expr RPAR {pass2} / unitname
  //    {pass} / bfunc LPAR expr RPAR {evalBfunc} / opttilde dfunc LPAR
  //    expr RPAR {evalUfunc} ;
  //=====================================================================
  boolean primary()
    {
      begin("primary");
      if (numexpr()) {sem.makeNumUnit(); return accept();}
      if (primary_0()) {sem.pass2(); return accept();}
      if (unitname()) {sem.pass(); return accept();}
      if (primary_1()) {sem.evalBfunc(); return accept();}
      if (primary_2()) {sem.evalUfunc(); return accept();}
      return reject();
    }
  
  //-------------------------------------------------------------------
  //  primary_0 = LPAR expr RPAR
  //-------------------------------------------------------------------
  boolean primary_0()
    {
      begin("");
      if (!LPAR()) return rejectInner();
      if (!expr()) return rejectInner();
      if (!RPAR()) return rejectInner();
      return acceptInner();
    }
  
  //-------------------------------------------------------------------
  //  primary_1 = bfunc LPAR expr RPAR
  //-------------------------------------------------------------------
  boolean primary_1()
    {
      begin("");
      if (!bfunc()) return rejectInner();
      if (!LPAR()) return rejectInner();
      if (!expr()) return rejectInner();
      if (!RPAR()) return rejectInner();
      return acceptInner();
    }
  
  //-------------------------------------------------------------------
  //  primary_2 = opttilde dfunc LPAR expr RPAR
  //-------------------------------------------------------------------
  boolean primary_2()
    {
      begin("");
      opttilde();
      if (!dfunc()) return rejectInner();
      if (!LPAR()) return rejectInner();
      if (!expr()) return rejectInner();
      if (!RPAR()) return rejectInner();
      return acceptInner();
    }
  
  //=====================================================================
  //  numexpr = number (BAR number)* {numexpr} ;
  //=====================================================================
  boolean numexpr()
    {
      begin("numexpr");
      if (!number()) return reject();
      while (numexpr_0());
      sem.numexpr();
      return accept();
    }
  
  //-------------------------------------------------------------------
  //  numexpr_0 = BAR number
  //-------------------------------------------------------------------
  boolean numexpr_0()
    {
      begin("");
      if (!BAR()) return rejectInner();
      if (!number()) return rejectInner();
      return acceptInner();
    }
  
  //=====================================================================
  //  number = mantissa exponent? space {number} ;
  //=====================================================================
  boolean number()
    {
      begin("number");
      if (!mantissa()) return reject();
      exponent();
      space();
      sem.number();
      return accept();
    }
  
  //=====================================================================
  //  mantissa = "." digits / digits ("." digits?)? ;
  //=====================================================================
  boolean mantissa()
    {
      begin("mantissa");
      if (mantissa_0()) return accept();
      if (mantissa_1()) return accept();
      return reject();
    }
  
  //-------------------------------------------------------------------
  //  mantissa_0 = "." digits
  //-------------------------------------------------------------------
  boolean mantissa_0()
    {
      begin("");
      if (!next('.')) return rejectInner();
      if (!digits()) return rejectInner();
      return acceptInner();
    }
  
  //-------------------------------------------------------------------
  //  mantissa_1 = digits ("." digits?)?
  //-------------------------------------------------------------------
  boolean mantissa_1()
    {
      begin("");
      if (!digits()) return rejectInner();
      mantissa_2();
      return acceptInner();
    }
  
  //-------------------------------------------------------------------
  //  mantissa_2 = "." digits?
  //-------------------------------------------------------------------
  boolean mantissa_2()
    {
      begin("");
      if (!next('.')) return rejectInner();
      digits();
      return acceptInner();
    }
  
  //=====================================================================
  //  exponent = [Ee] sign? digits ;
  //=====================================================================
  boolean exponent()
    {
      begin("exponent");
      if (!nextIn("Ee")) return reject();
      sign();
      if (!digits()) return reject();
      return accept();
    }
  
  //=====================================================================
  //  sign (sign) = [+-] ;
  //=====================================================================
  boolean sign()
    {
      begin("sign","sign");
      if (!nextIn("+-")) return reject();
      return accept();
    }
  
  //=====================================================================
  //  digits = digit+ ;
  //=====================================================================
  boolean digits()
    {
      begin("digits");
      if (!digit()) return reject();
      while (digit());
      return accept();
    }
  
  //=====================================================================
  //  digit = [0-9] ;
  //=====================================================================
  boolean digit()
    {
      begin("digit");
      if (!nextIn('0','9')) return reject();
      return accept();
    }
  
  //=====================================================================
  //  word = ![.0123456789~] namechar+ ;
  //=====================================================================
  boolean word()
    {
      begin("word");
      if (!aheadNotIn(".0123456789~")) return reject();
      if (!namechar()) return reject();
      while (namechar());
      return accept();
    }
  
  //=====================================================================
  //  namechar (more name) = ![\t\n^ +-*/|()] _ ;
  //=====================================================================
  boolean namechar()
    {
      begin("namechar","more name");
      if (!aheadNotIn("	\n^ +-*/|()")) return reject();
      if (!next()) return reject();
      return accept();
    }
  
  //=====================================================================
  //  opttilde (~) = TILDE? ;
  //=====================================================================
  boolean opttilde()
    {
      begin("opttilde","~");
      TILDE();
      return accept();
    }
  
  //=====================================================================
  //  unitname (unit name) = word space {&unitname} ;
  //=====================================================================
  boolean unitname()
    {
      begin("unitname","unit name");
      if (!word()) return reject();
      space();
      if (sem.unitname()) return accept();
      return reject();
    }
  
  //=====================================================================
  //  bfunc (function name) = word space {&bfunc} ;
  //=====================================================================
  boolean bfunc()
    {
      begin("bfunc","function name");
      if (!word()) return reject();
      space();
      if (sem.bfunc()) return accept();
      return reject();
    }
  
  //=====================================================================
  //  dfunc (function name) = word space {&ufunc} ;
  //=====================================================================
  boolean dfunc()
    {
      begin("dfunc","function name");
      if (!word()) return reject();
      space();
      if (sem.ufunc()) return accept();
      return reject();
    }
  
  //=====================================================================
  //  BAR (|) = "|" space ;
  //=====================================================================
  boolean BAR()
    {
      begin("BAR","|");
      if (!next('|')) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  HAT (^) = "^" space ;
  //=====================================================================
  boolean HAT()
    {
      begin("HAT","^");
      if (!next('^')) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  TILDE (~) = "~" space ;
  //=====================================================================
  boolean TILDE()
    {
      begin("TILDE","~");
      if (!next('~')) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  LPAR (() = "(" space ;
  //=====================================================================
  boolean LPAR()
    {
      begin("LPAR","(");
      if (!next('(')) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  MINUS (-) = "-" space ;
  //=====================================================================
  boolean MINUS()
    {
      begin("MINUS","-");
      if (!next('-')) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  PLUS (+) = "+" space ;
  //=====================================================================
  boolean PLUS()
    {
      begin("PLUS","+");
      if (!next('+')) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  RPAR ()) = ")" space ;
  //=====================================================================
  boolean RPAR()
    {
      begin("RPAR",")");
      if (!next(')')) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  SLASH (/) = "/" space ;
  //=====================================================================
  boolean SLASH()
    {
      begin("SLASH","/");
      if (!next('/')) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  STARSTAR (**) = "**" space ;
  //=====================================================================
  boolean STARSTAR()
    {
      begin("STARSTAR","**");
      if (!next("**")) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  STAR (*) = "*" !"*" space ;
  //=====================================================================
  boolean STAR()
    {
      begin("STAR","*");
      if (!next('*')) return reject();
      if (!aheadNot('*')) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  PER ('per') = "per" &[\t\n^ +-*/|()] space ;
  //=====================================================================
  boolean PER()
    {
      begin("PER","'per'");
      if (!next("per")) return reject();
      if (!aheadIn("	\n^ +-*/|()")) return reject();
      space();
      return accept();
    }
  
  //=====================================================================
  //  space = [ \t]* {space} ;
  //=====================================================================
  boolean space()
    {
      begin("space");
      while (nextIn(" 	"));
      sem.space();
      return accept();
    }
  
  //=====================================================================
  //  EOT (end of input) = !_ ;
  //=====================================================================
  boolean EOT()
    {
      begin("EOT","end of input");
      if (!aheadNot()) return reject();
      return accept();
    }
  
}
