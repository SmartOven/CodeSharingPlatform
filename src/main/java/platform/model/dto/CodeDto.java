package platform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CodeDto {
    private String code;
    private Long time;
    private Long views;

    private String date;

    @JsonProperty
    public String getDate() {
        return date;
    }

    @JsonIgnore
    public void setDate(String date) {
        this.date = date;
    }
}
