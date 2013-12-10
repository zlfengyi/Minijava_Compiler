//
// Generated by JTB 1.3.2
//

package piglet.syntaxtree;

/**
 * Represents an grammar optional node, e.g. ( A )? or [ A ]
 */
public class NodeOptional implements Node {
   public NodeOptional() {
      node = null;
   }

   public NodeOptional(Node n) {
      addNode(n);
   }

   public void addNode(Node n)  {
      if ( node != null)                // Oh oh!
         throw new Error("Attempt to set optional node twice");

      node = n;
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
   public boolean present()   { return node != null; }

   public Node node;
   
   @Override
   public String toString() {
	   return "NodeOptional";
   }
}

