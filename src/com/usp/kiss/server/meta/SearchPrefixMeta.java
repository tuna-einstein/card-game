package com.usp.kiss.server.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2015-05-17 20:02:36")
/** */
public final class SearchPrefixMeta extends org.slim3.datastore.ModelMeta<com.usp.kiss.shared.model.SearchPrefix> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.SearchPrefix, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.SearchPrefix, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.usp.kiss.shared.model.SearchPrefix> prefix = new org.slim3.datastore.StringAttributeMeta<com.usp.kiss.shared.model.SearchPrefix>(this, "prefix", "prefix");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.SearchPrefix, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.SearchPrefix, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final SearchPrefixMeta slim3_singleton = new SearchPrefixMeta();

    /**
     * @return the singleton
     */
    public static SearchPrefixMeta get() {
       return slim3_singleton;
    }

    /** */
    public SearchPrefixMeta() {
        super("SearchPrefix", com.usp.kiss.shared.model.SearchPrefix.class);
    }

    @Override
    public com.usp.kiss.shared.model.SearchPrefix entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.usp.kiss.shared.model.SearchPrefix model = new com.usp.kiss.shared.model.SearchPrefix();
        model.setKey(entity.getKey());
        model.setPrefix((java.lang.String) entity.getProperty("prefix"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.usp.kiss.shared.model.SearchPrefix m = (com.usp.kiss.shared.model.SearchPrefix) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("prefix", m.getPrefix());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.usp.kiss.shared.model.SearchPrefix m = (com.usp.kiss.shared.model.SearchPrefix) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.usp.kiss.shared.model.SearchPrefix m = (com.usp.kiss.shared.model.SearchPrefix) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.usp.kiss.shared.model.SearchPrefix m = (com.usp.kiss.shared.model.SearchPrefix) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        com.usp.kiss.shared.model.SearchPrefix m = (com.usp.kiss.shared.model.SearchPrefix) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        com.usp.kiss.shared.model.SearchPrefix m = (com.usp.kiss.shared.model.SearchPrefix) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getPrefix() != null){
            writer.setNextPropertyName("prefix");
            encoder0.encode(writer, m.getPrefix());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.usp.kiss.shared.model.SearchPrefix jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.usp.kiss.shared.model.SearchPrefix m = new com.usp.kiss.shared.model.SearchPrefix();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("prefix");
        m.setPrefix(decoder0.decode(reader, m.getPrefix()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}