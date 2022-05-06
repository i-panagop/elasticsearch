package com.project.elasticsearch.utils;

public class Constants {

    public static final String GENERIC_INDEX = "generic";
    public static final String ARTIST_INDEX = "artist";
    public static final String ARTWORK_INDEX = "artwork";
    public static final String CURATOR_ARTWORK_INDEX = "curatorartwork";
    public static final String CURATOR_INDEX = "curator";
    public static final String GALLERY_INDEX = "gallery";
    public static final String LOCATION_INDEX = "location";
    public static final String SHOW_INDEX = "show";
    public static final String USER_INDEX = "user";

    public static final String GENERIC_MAPPING_JSON = "generic_mapping.json";
    public static final String ARTIST_MAPPING_JSON = "artist_mapping.json";
    public static final String ARTWORK_MAPPING_JSON = "artwork_mapping.json";
    public static final String CURATOR_ARTWORK_MAPPING_JSON = "curator_artwork_mapping.json";
    public static final String CURATOR_MAPPING_JSON = "curator_mapping.json";
    public static final String GALLERY_MAPPING_JSON = "gallery_mapping.json";
    public static final String LOCATION_MAPPING_JSON = "location_mapping.json";
    public static final String SHOW_MAPPING_JSON = "show_mapping.json";
    public static final String USER_MAPPING_JSON = "user_mapping.json";

    public static final String ERROR_READING_FILE = "Error while reading input file";
    public static final String ERROR_DURING_FULL_INDEX = "Error during fullIndex for index : {}";
    public static final String ERROR_DURING_TEXT_SEARCH_FOR_INDEX = "Error during text search for index: {}";
    public static final String STARTING_FULL_EXPORT_TO_INDEX = "Starting full export to elastic for : {}";
    public static final String FINISHED_FULL_EXPORT_TO_INDEX = "Finished full export to elastic for : {} in {}s";
    public static final String STARTED_MERGING_FUTURES = "Starting merging futures";
    public static final String FINISHED_MERGING_FUTURES = "Finished merging futures: {}";
    public static final String _LOCAL = "_local";
    public static final String STATE = "state";
    public static final String VISIBLE = "visible";
    public static final String APPROVED = "approved";
    public static final String TITLE_ANALYZED = "title.analyzed";
    public static final String ARTIST_ID_ANALYZED = "artist.id.analyzed";
    public static final String GALLERIES_ID_ANALYZED = "galleries.id.analyzed";
    public static final String SUBMITTED_BY_ID_ANALYZED = "submittedBy.id.analyzed";
    public static final String GALLERIES = "galleries";
    public static final String ID_ANALYZED = "id.analyzed";
    public static final String EMAIL_ANALYZED = "email.analyzed";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
}
