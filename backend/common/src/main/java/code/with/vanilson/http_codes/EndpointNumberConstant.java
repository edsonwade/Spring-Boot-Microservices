package code.with.vanilson.http_codes;

public final class EndpointNumberConstant {

    // HTTP Status Codes
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int ACCEPTED = 202;
    public static final int NO_CONTENT = 204;
    public static final int MOVED_PERMANENTLY = 301;
    public static final int FOUND = 302;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int CONFLICT = 409;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;

    // Common Port Numbers
    public static final int HTTP_PORT = 80;
    public static final int HTTPS_PORT = 443;
    public static final int FTP_PORT = 21;
    public static final int SSH_PORT = 22;
    public static final int SMTP_PORT = 25;
    public static final int DNS_PORT = 53;
    public static final int DHCP_PORT = 67;
    public static final int TELNET_PORT = 23;

    // Database Connection Ports
    public static final int MYSQL_PORT = 3306;
    public static final int POSTGRESQL_PORT = 5432;
    public static final int ORACLE_PORT = 1521;
    public static final int MONGODB_PORT = 27017;
    public static final int REDIS_PORT = 6379;
    public static final int ELASTICSEARCH_PORT = 9200;

    // Common Endpoint Numbers
    public static final int DEFAULT_HTTP_PORT = 8080;
    public static final int DEFAULT_HTTPS_PORT = 8443;
    public static final int DEFAULT_DATABASE_PORT = 3306;
    public static final int DEFAULT_REDIS_PORT = 6379;

    // Private constructor to prevent instantiation
    private EndpointNumberConstant() {
        throw new AssertionError("Cannot instantiate EndpointNumbers");
    }
}
