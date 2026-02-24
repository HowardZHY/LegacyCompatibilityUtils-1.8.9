package bspkrs.util;

import java.io.File;

import net.minecraftforge.common.config.*;

/**
 * @apiNote Deprecated, Dummy Class
 */
@SuppressWarnings("unused")
public class BSConfiguration extends Configuration {

    public final File fileRef;

    public BSConfiguration(File file) {
        super(file);
        this.fileRef = file;
    }

    @Override
    public File getConfigFile() {
        return this.fileRef;
    }

    @Override
    public boolean renameProperty(String category, String oldPropName, String newPropName) {
        return super.renameProperty(category, oldPropName, newPropName);
    }

    @Override
    public boolean moveProperty(String oldCategory, String propName, String newCategory) {
        return super.renameProperty(oldCategory, propName, newCategory);
    }

    @Override
    public String getString(String name, String category, String defaultValue, String comment) {
        return this.getString(name, category, defaultValue, comment, new String[0]);
    }

    @Override
    public String getString(String name, String category, String defaultValue, String comment, String[] validValues) {
        return this.get(category, name, defaultValue, comment).getString();
    }

    @Override
    public String[] getStringList(String name, String category, String[] defaultValues, String comment) {
        return this.getStringList(name, category, defaultValues, comment, new String[0]);
    }

    @Override
    public String[] getStringList(String name, String category, String[] defaultValue, String comment, String[] validValues) {
        return this.get(category, name, defaultValue, comment).getStringList();
    }

    @Override
    public boolean getBoolean(String name, String category, boolean defaultValue, String comment) {
        return this.get(name, category, defaultValue, comment).getBoolean(defaultValue);
    }

    @Override
    public int getInt(String name, String category, int defaultValue, int minValue, int maxValue, String comment) {
        Property prop = this.get(category, name, defaultValue, comment);
        return prop.getInt(defaultValue) < minValue ? minValue : (Math.min(prop.getInt(defaultValue), maxValue));
    }

    @Override
    public float getFloat(String name, String category, float defaultValue, float minValue, float maxValue, String comment) {
        Property prop = this.get(category, name, Float.toString(defaultValue), comment);
        try {
            return Float.parseFloat(prop.getString()) < minValue ? minValue : (Math.min(Float.parseFloat(prop.getString()), maxValue));
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }
}
