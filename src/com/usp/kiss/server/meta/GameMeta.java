package com.usp.kiss.server.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2015-05-17 20:02:36")
/** */
public final class GameMeta extends org.slim3.datastore.ModelMeta<com.usp.kiss.shared.model.Game> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Game, java.util.Date> date = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Game, java.util.Date>(this, "date", "date", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Game, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Game, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Game, java.lang.Integer> numPlayers = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Game, java.lang.Integer>(this, "numPlayers", "numPlayers", int.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.usp.kiss.shared.model.Game> ownerEmail = new org.slim3.datastore.StringAttributeMeta<com.usp.kiss.shared.model.Game>(this, "ownerEmail", "ownerEmail");

    /** */
    public final org.slim3.datastore.StringCollectionAttributeMeta<com.usp.kiss.shared.model.Game, java.util.List<java.lang.String>> players = new org.slim3.datastore.StringCollectionAttributeMeta<com.usp.kiss.shared.model.Game, java.util.List<java.lang.String>>(this, "players", "players", java.util.List.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Game, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.usp.kiss.shared.model.Game, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final GameMeta slim3_singleton = new GameMeta();

    /**
     * @return the singleton
     */
    public static GameMeta get() {
       return slim3_singleton;
    }

    /** */
    public GameMeta() {
        super("Game", com.usp.kiss.shared.model.Game.class);
    }

    @Override
    public com.usp.kiss.shared.model.Game entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.usp.kiss.shared.model.Game model = new com.usp.kiss.shared.model.Game();
        model.setDate((java.util.Date) entity.getProperty("date"));
        model.setKey(entity.getKey());
        model.setNumPlayers(longToPrimitiveInt((java.lang.Long) entity.getProperty("numPlayers")));
        model.setOwnerEmail((java.lang.String) entity.getProperty("ownerEmail"));
        model.setPlayers(toList(java.lang.String.class, entity.getProperty("players")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.usp.kiss.shared.model.Game m = (com.usp.kiss.shared.model.Game) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("date", m.getDate());
        entity.setProperty("numPlayers", m.getNumPlayers());
        entity.setProperty("ownerEmail", m.getOwnerEmail());
        entity.setProperty("players", m.getPlayers());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.usp.kiss.shared.model.Game m = (com.usp.kiss.shared.model.Game) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.usp.kiss.shared.model.Game m = (com.usp.kiss.shared.model.Game) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.usp.kiss.shared.model.Game m = (com.usp.kiss.shared.model.Game) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        com.usp.kiss.shared.model.Game m = (com.usp.kiss.shared.model.Game) model;
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
        com.usp.kiss.shared.model.Game m = (com.usp.kiss.shared.model.Game) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getDate() != null){
            writer.setNextPropertyName("date");
            encoder0.encode(writer, m.getDate());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        writer.setNextPropertyName("numPlayers");
        encoder0.encode(writer, m.getNumPlayers());
        if(m.getOwnerEmail() != null){
            writer.setNextPropertyName("ownerEmail");
            encoder0.encode(writer, m.getOwnerEmail());
        }
        if(m.getPlayers() != null){
            writer.setNextPropertyName("players");
            writer.beginArray();
            for(java.lang.String v : m.getPlayers()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.usp.kiss.shared.model.Game jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.usp.kiss.shared.model.Game m = new com.usp.kiss.shared.model.Game();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("date");
        m.setDate(decoder0.decode(reader, m.getDate()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("numPlayers");
        m.setNumPlayers(decoder0.decode(reader, m.getNumPlayers()));
        reader = rootReader.newObjectReader("ownerEmail");
        m.setOwnerEmail(decoder0.decode(reader, m.getOwnerEmail()));
        reader = rootReader.newObjectReader("players");
        {
            java.util.ArrayList<java.lang.String> elements = new java.util.ArrayList<java.lang.String>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("players");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.lang.String v = decoder0.decode(reader, (java.lang.String)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setPlayers(elements);
            }
        }
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}