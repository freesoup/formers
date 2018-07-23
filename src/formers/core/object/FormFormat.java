package formers.core.object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class to represent the layout of a form. To be used when creating/populating a form, as well as viewing the details
 * of a form.
 * 
 * @author jackietan
 *
 */
public class FormFormat {
    private String preamble;
    private String ID;
    private String owner;
    private Date dateCreated;
    private Date dateExpiry;
    private List<Input> fields;

    public FormFormat() {
        this.dateCreated = new Date();
        this.fields = new ArrayList<Input>();
    }

    public void addPreamble(String info) {
        this.preamble = info;
    }

    public void addID(String id) {
        this.ID = Objects.requireNonNull(id, "Fatal Error encountered assigning ID to the form.");
    }

    public void addAdmin(String owner) {
        this.owner = owner;
    }

    public void setDateCreated(Date date) {
        this.dateCreated = date;
    }

    public void setDateExpirty(Date date) {
        this.dateExpiry = date;
    }

    public void addInput(Input field) {
        fields.add(field);
    }
}
