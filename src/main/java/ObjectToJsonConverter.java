import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonConverter {
    public String toJson(Object object) {
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}