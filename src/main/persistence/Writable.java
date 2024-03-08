package persistence;

import org.json.JSONObject;

// Code is from JsonSerializationDemo from CPSC 210
// Interface for objects that can be written to json
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
