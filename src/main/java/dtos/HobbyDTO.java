package dtos;

import entities.Address;
import entities.Hobby;

import java.util.ArrayList;
import java.util.List;

public class HobbyDTO {

    private String dto_name;
    private String dto_wikiLink;
    private String dto_category;
    private String dto_type;

    public HobbyDTO() {
    }

    public HobbyDTO(HobbyDTO h) {
    }

    public static List<HobbyDTO> getDtos(List<HobbyDTO> hb) {
        List<HobbyDTO> hDTO = new ArrayList();
        hb.forEach(h -> hDTO.add(new HobbyDTO(h)));
        return hDTO;
    }

    public HobbyDTO(Hobby hb) {
        this.dto_name = hb.getName();
        this.dto_wikiLink = hb.getWikiLink();
        this.dto_category = hb.getCategory();
        this.dto_type = hb.getType();
    }

    public String getDto_name() {
        return dto_name;
    }

    public void setDto_name(String dto_name) {
        this.dto_name = dto_name;
    }

    public String getDto_wikiLink() {
        return dto_wikiLink;
    }

    public void setDto_wikiLink(String dto_wikiLink) {
        this.dto_wikiLink = dto_wikiLink;
    }

    public String getDto_category() {
        return dto_category;
    }

    public void setDto_category(String dto_category) {
        this.dto_category = dto_category;
    }

    public String getDto_type() {
        return dto_type;
    }

    public void setDto_type(String dto_type) {
        this.dto_type = dto_type;
    }
}
