package tool;

import java.io.File;
import java.io.FilenameFilter;

public class WeFilenameFilter implements FilenameFilter {
	private String regex;

	public WeFilenameFilter(String regex) {
		this.regex = regex;
	}

	@Override
	public boolean accept(File dir, String name) {
		if (name.matches(this.regex)) {
			return true;
		}
		return false;
	}
}
