package guru.springframework.sfgpetclinic.model;

/**
 * Created by jt on 7/13/18.
 */
public class PetType extends BaseEntity {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
