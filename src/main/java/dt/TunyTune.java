package dt;

import java.util.Arrays;
import java.util.List;

/**
 * TunyTune
 */
public class TunyTune {

    private String tagLine;

	public String getTagLine() {
		return this.tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
    }

    private List<Tuny> tunies;

	public List<Tuny> getTunies() {
		return this.tunies;
	}

	public void setTunies(List<Tuny> tunies) {
		this.tunies = tunies;
	}

    public TunyTune() {
        this.tagLine = "DUYTRUNG THE CAT";
        this.tunies = Arrays.asList(TunyFactory.getTunyList());
    }

}
