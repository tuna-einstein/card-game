package com.usp.kiss.server.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2015-05-14 13:42:27")
/** */
public final class EpisodeMeta extends org.slim3.datastore.ModelMeta<com.usp.kiss.shared.model.Episode> {

    /** */
    public final org.slim3.datastore.CoreUnindexedAttributeMeta<com.usp.kiss.shared.model.Episode, int[]> actual = new org.slim3.datastore.CoreUnindexedAttributeMeta<com.usp.kiss.shared.model.Episode, int[]>(this, "actual", "actual", int[].class);

    /** */
    public final org.slim3.datastore.CoreUnindexedAttributeMeta<com.usp.kiss.shared.model.Episode, int[]> expected = new org.slim3.datastore.CoreUnindexedAttributeMeta<com.usp.kiss.shared.model.Episode, int[]>(this, "expected", "expected", int[].class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Episode, java.lang.Long> gameId = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Episode, java.lang.Long>(this, "gameId", "gameId", long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Episode, java.lang.Integer> id = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Episode, java.lang.Integer>(this, "id", "id", int.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Episode, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Episode, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.usp.kiss.shared.model.Episode> title = new org.slim3.datastore.StringAttributeMeta<com.usp.kiss.shared.model.Episode>(this, "title", "title");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Episode, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Episode, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final EpisodeMeta slim3_singleton = new EpisodeMeta();

    /**
     * @return the singleton
     */
    public static EpisodeMeta get() {
       return slim3_singleton;
    }

    /** */
    public EpisodeMeta() {
        super("Episode", com.usp.kiss.shared.model.Episode.class);
    }

    @Override
    public com.usp.kiss.shared.model.Episode entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.usp.kiss.shared.model.Episode model = new com.usp.kiss.shared.model.Episode();
        int[] _actual = blobToSerializable((com.google.appengine.api.datastore.Blob) entity.getProperty("actual"));
        model.setActual(_actual);
        int[] _expected = blobToSerializable((com.google.appengine.api.datastore.Blob) entity.getProperty("expected"));
        model.setExpected(_expected);
        model.setGameId(longToPrimitiveLong((java.lang.Long) entity.getProperty("gameId")));
        model.setId(longToPrimitiveInt((java.lang.Long) entity.getProperty("id")));
        model.setKey(entity.getKey());
        model.setTitle((java.lang.String) entity.getProperty("title"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.usp.kiss.shared.model.Episode m = (com.usp.kiss.shared.model.Episode) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setUnindexedProperty("actual", serializableToBlob(m.getActual()));
        entity.setUnindexedProperty("expected", serializableToBlob(m.getExpected()));
        entity.setProperty("gameId", m.getGameId());
        entity.setProperty("id", m.getId());
        entity.setProperty("title", m.getTitle());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.usp.kiss.shared.model.Episode m = (com.usp.kiss.shared.model.Episode) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.usp.kiss.shared.model.Episode m = (com.usp.kiss.shared.model.Episode) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.usp.kiss.shared.model.Episode m = (com.usp.kiss.shared.model.Episode) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        com.usp.kiss.shared.model.Episode m = (com.usp.kiss.shared.model.Episode) model;
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
        com.usp.kiss.shared.model.Episode m = (com.usp.kiss.shared.model.Episode) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getActual() != null){
            writer.setNextPropertyName("actual");
            // int[](int[]) is not supported.
        }
        if(m.getExpected() != null){
            writer.setNextPropertyName("expected");
            // int[](int[]) is not supported.
        }
        writer.setNextPropertyName("gameId");
        encoder0.encode(writer, m.getGameId());
        writer.setNextPropertyName("id");
        encoder0.encode(writer, m.getId());
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getTitle() != null){
            writer.setNextPropertyName("title");
            encoder0.encode(writer, m.getTitle());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.usp.kiss.shared.model.Episode jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.usp.kiss.shared.model.Episode m = new com.usp.kiss.shared.model.Episode();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("actual");
        // int[](int[]) is not supported.
        reader = rootReader.newObjectReader("expected");
        // int[](int[]) is not supported.
        reader = rootReader.newObjectReader("gameId");
        m.setGameId(decoder0.decode(reader, m.getGameId()));
        reader = rootReader.newObjectReader("id");
        m.setId(decoder0.decode(reader, m.getId()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("title");
        m.setTitle(decoder0.decode(reader, m.getTitle()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}