//
// Generated by JTB 1.3.2
//

package piglet.syntaxtree;

/**
 * Grammar production:
 * f0 -> "CALL"
 * f1 -> Exp()
 * f2 -> "("
 * f3 -> ( Exp() )*
 * f4 -> ")"
 */
public class Call implements Node {
   public NodeToken f0;
   public Exp f1;
   public NodeToken f2;
   public NodeListOptional f3;
   public NodeToken f4;

   public Call(NodeToken n0, Exp n1, NodeToken n2, NodeListOptional n3, NodeToken n4) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
   }

   public Call(Exp n0, NodeListOptional n1) {
      f0 = new NodeToken("CALL");
      f1 = n0;
      f2 = new NodeToken("(");
      f3 = n1;
      f4 = new NodeToken(")");
   }

   public void accept(piglet.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(piglet.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(piglet.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(piglet.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}
