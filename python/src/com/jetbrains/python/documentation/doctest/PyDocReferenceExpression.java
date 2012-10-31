package com.jetbrains.python.documentation.doctest;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.python.psi.PyExpression;
import com.jetbrains.python.psi.PyImportStatement;
import com.jetbrains.python.psi.impl.PyReferenceExpressionImpl;
import com.jetbrains.python.psi.impl.references.PyImportReference;
import com.jetbrains.python.psi.impl.references.PyQualifiedReference;
import com.jetbrains.python.psi.resolve.PyResolveContext;
import org.jetbrains.annotations.NotNull;

/**
 *
 * User : ktisha
 */
public class PyDocReferenceExpression extends PyReferenceExpressionImpl {

  public PyDocReferenceExpression(ASTNode astNode) {
    super(astNode);
  }

  @NotNull
  public PsiPolyVariantReference getReference(PyResolveContext context) {
    final PyExpression qualifier = getQualifier();
    if (qualifier != null) {
      return new PyQualifiedReference(this, context);
    }
    else if (PsiTreeUtil.getParentOfType(this, PyImportStatement.class) != null)
      return new PyImportReference(this, context);
    return new PyDocReference(this, context);
  }
}

