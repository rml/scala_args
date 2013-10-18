import scala.xml.{Node, Elem}
import scala.xml.transform.RewriteRule
import scalaz.Scalaz._
import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseTransformerFactory
import com.typesafe.sbteclipse.core.Validation
import com.typesafe.sbteclipse.core.EclipsePlugin.EclipseClasspathEntry

object IvyCacheRewriteRule extends RewriteRule {

  override def transform(parent: Node): Seq[Node] = {
  
    parent match {
      
      case <classpath>{children @ _*}</classpath> => 

        val chld = for(child <- children) yield {
        
          if((child \ "@kind").text == "lib"){
            val ivyPath = child.\("@path").text.split(".ivy2/cache/").toList.tail.mkString
            <classpathentry kind="var" path={"IVY2_CACHE/" + ivyPath}/>
          } else {
            child
          }
        }
        <classpath>{chld}</classpath>

      case other => other
    }  
  }
}

object IvyCacheTransformer extends EclipseTransformerFactory[RewriteRule] {

  override def createTransformer(ref: sbt.ProjectRef, state: sbt.State): Validation[RewriteRule] = {
    IvyCacheRewriteRule.success
  }
}
