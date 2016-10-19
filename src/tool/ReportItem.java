package tool;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


/**
 * ReportItem
 * @author KISSCO
 *
 */
public class ReportItem {
	
	@XmlAttribute
	public String type;
	@XmlAttribute
	public String express;
	@XmlAttribute
	public String option1;
	@XmlAttribute
	public boolean isLst = false;
	@XmlElement
	public ArrayList<String> lstValue;

}
