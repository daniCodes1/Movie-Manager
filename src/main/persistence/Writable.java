package persistence;

import org.json.JSONObject;

// based on JsonSerializationDemo ; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}