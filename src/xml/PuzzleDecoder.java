package xml;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class PuzzleDecoder {
		private Document root ;
		private FlowPuzzles racine = new FlowPuzzles();
		
		public FlowPuzzles getRacine() {
			return racine;
		}
		public PuzzleDecoder(Document doc)
		{
			this.root = doc ;
		}
		public void  startDecoding()  
		{
			this.decodePuzzle() ;
		}
		
		private void decodePuzzle()   {
			
			NodeList nodes = this.root.getElementsByTagName("flowpuzzle") ;
			if (nodes.getLength() == 0 )
				return ;
			for (int j = 0 ; j< nodes.getLength() ; j++)
			{
				Node node = nodes.item(j) ;
				FlowPuzzle p = new FlowPuzzle() ;
				String width = node.getAttributes().getNamedItem("width").getTextContent() ;
				String height = node.getAttributes().getNamedItem("height").getTextContent() ;
				p.setWidth(Integer.parseInt(width));
				p.setHeight(Integer.parseInt(height));
				NodeList lines = node.getChildNodes() ;
				for (int i = 0 ; i< lines.getLength() ; i++)
				{
					if (lines.item(i).getNodeName().equals("line"))
					{
						this.decodeLine(p, lines.item(i));
					}
				}
				racine.getPuzzles().add(p) ;
			}
			
		}
		private void decodeLine(FlowPuzzle p, Node line)  
		{
			NodeList attrs = line.getChildNodes() ;
			Line l = new Line() ;
			for (int i = 0 ; i< attrs.getLength() ; i++)
			{
				
				if (attrs.item(i).getNodeName().equals("colors"))
				{
					String color = attrs.item(i).getAttributes().getNamedItem("value").getTextContent() ;
					l.setColor(color);
				}
				if (attrs.item(i).getNodeName().equals("src"))
				{
					String x = attrs.item(i).getAttributes().getNamedItem("x").getTextContent() ;
					l.setxSrc(Integer.parseInt(x));
					String y = attrs.item(i).getAttributes().getNamedItem("y").getTextContent() ;
					l.setySrc(Integer.parseInt(y));					
				}
				if (attrs.item(i).getNodeName().equals("dst"))
				{
					String x = attrs.item(i).getAttributes().getNamedItem("x").getTextContent() ;
					l.setxDest(Integer.parseInt(x));
					String y = attrs.item(i).getAttributes().getNamedItem("y").getTextContent() ;
					l.setyDest(Integer.parseInt(y));					
				}
			}	
			p.getLines().add(l);
			
		}
		
}
