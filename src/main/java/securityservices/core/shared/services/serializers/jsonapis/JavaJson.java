package securityservices.core.shared.services.serializers.jsonapis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import securityservices.core.shared.services.serializers.Json;
//clase per al tractament de informacio en format json prenent com a base la llibreria java-json.jar

public final class JavaJson implements Json {

    protected JSONObject jObject = null;
    protected boolean correct = false;

    public JavaJson() {
    }

    public JavaJson(String data) {
        this.set(data);
    }

    @Override
    public void set(String data) {
        try {
            jObject = new JSONObject(data);
            correct = true;
        } catch (JSONException ex) {
            setXmlData(data);
        }
    }

    @Override
    public void setXmlData(String data) {
        try {
            jObject = new JSONObject(XML.toJSONObject(data).toString());
            correct = true;
        } catch (JSONException ex) {
            correct = false;
        }
    }

    @Override
    public void set(String attrib, String value) {
        if (jObject == null) {
            jObject = new JSONObject();
        }

        try {
            jObject.accumulate(attrib, value);
            correct = true;
        } catch (JSONException ex) {
            correct = false;
        }
    }

    @Override
    public void set(String attrib, Json jdata) {
        if (jObject == null) {
            jObject = new JSONObject();
        }
        try {
            JSONObject jattrib = new JSONObject(jdata.toString());
            jObject.accumulate(attrib, jattrib);
            correct = true;
        } catch (JSONException ex) {
            correct = false;
        }

    }

    @Override
    public String get(String attrib) {
        if (has(attrib)) {
            try {
                return jObject.get(attrib).toString().trim();
            } catch (JSONException ex) {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean has(String attrib) {
        if (correct) {
            return jObject.has(attrib);
        } else {
            return false;
        }
    }

    @Override
    public int getArraySize(String atrib) {
        if (has(atrib)) {
            try {
                return jObject.getJSONArray(atrib).length();
            } catch (JSONException ex) {
                return -1;
            }
        }
        return -1;
    }

    @Override
    public String getArrayValue(String data, int pos) {
        if (this.has(data)) {
            try {
                JSONArray array = jObject.getJSONArray(data);
                return array.getString(pos).trim();
            } catch (JSONException ex) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Json getArrayObj(String data, int pos) {
        if (this.has(data)) {
            try {
                JSONArray array = jObject.getJSONArray(data);
                return new JavaJson(array.optJSONObject(pos).toString());
            } catch (JSONException ex) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Json getJResult(String data) {
        if (this.has(data)) {
            try {
                return new JavaJson(jObject.getJSONObject(data).toString());
            } catch (JSONException ex) {
                return null;
            }
        }
        return null;
    }

    @Override
    public void remove(String key) {
        jObject.remove(key);
    }

    @Override
    public String toString() {
        return jObject.toString();
    }

    @Override
    public String toXml() {
        if (correct) {
            try {
                return XML.toString(this.jObject);
            } catch (JSONException ex) {
                return "";
            }
        }
        return "";
    }

    @Override
    public void removeAll() {
        jObject = null;
        correct = false;
    }
}
