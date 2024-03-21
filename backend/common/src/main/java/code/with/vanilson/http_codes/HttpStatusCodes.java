package code.with.vanilson.http_codes;

import org.springframework.http.HttpStatus;

public final class HttpStatusCodes {

    // 1xx Informational
    public static final HttpStatus CONTINUE = HttpStatus.CONTINUE;
    public static final HttpStatus SWITCHING_PROTOCOLS = HttpStatus.SWITCHING_PROTOCOLS;
    public static final HttpStatus PROCESSING = HttpStatus.PROCESSING;

    // 2xx Success
    public static final HttpStatus OK = HttpStatus.OK;
    public static final HttpStatus CREATED = HttpStatus.CREATED;
    public static final HttpStatus ACCEPTED = HttpStatus.ACCEPTED;
    public static final HttpStatus NON_AUTHORITATIVE_INFORMATION = HttpStatus.NON_AUTHORITATIVE_INFORMATION;
    public static final HttpStatus NO_CONTENT = HttpStatus.NO_CONTENT;
    public static final HttpStatus RESET_CONTENT = HttpStatus.RESET_CONTENT;
    public static final HttpStatus PARTIAL_CONTENT = HttpStatus.PARTIAL_CONTENT;
    public static final HttpStatus MULTI_STATUS = HttpStatus.MULTI_STATUS;
    public static final HttpStatus ALREADY_REPORTED = HttpStatus.ALREADY_REPORTED;
    public static final HttpStatus IM_USED = HttpStatus.IM_USED;

    // 3xx Redirection
    public static final HttpStatus MULTIPLE_CHOICES = HttpStatus.MULTIPLE_CHOICES;
    public static final HttpStatus MOVED_PERMANENTLY = HttpStatus.MOVED_PERMANENTLY;
    public static final HttpStatus FOUND = HttpStatus.FOUND;
    public static final HttpStatus SEE_OTHER = HttpStatus.SEE_OTHER;
    public static final HttpStatus NOT_MODIFIED = HttpStatus.NOT_MODIFIED;
    public static final HttpStatus TEMPORARY_REDIRECT = HttpStatus.TEMPORARY_REDIRECT;
    public static final HttpStatus PERMANENT_REDIRECT = HttpStatus.PERMANENT_REDIRECT;

    // 4xx Client Error
    public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    public static final HttpStatus UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
    public static final HttpStatus PAYMENT_REQUIRED = HttpStatus.PAYMENT_REQUIRED;
    public static final HttpStatus FORBIDDEN = HttpStatus.FORBIDDEN;
    public static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
    public static final HttpStatus METHOD_NOT_ALLOWED = HttpStatus.METHOD_NOT_ALLOWED;
    public static final HttpStatus NOT_ACCEPTABLE = HttpStatus.NOT_ACCEPTABLE;
    public static final HttpStatus PROXY_AUTHENTICATION_REQUIRED = HttpStatus.PROXY_AUTHENTICATION_REQUIRED;
    public static final HttpStatus REQUEST_TIMEOUT = HttpStatus.REQUEST_TIMEOUT;
    public static final HttpStatus CONFLICT = HttpStatus.CONFLICT;
    public static final HttpStatus GONE = HttpStatus.GONE;
    public static final HttpStatus LENGTH_REQUIRED = HttpStatus.LENGTH_REQUIRED;
    public static final HttpStatus PRECONDITION_FAILED = HttpStatus.PRECONDITION_FAILED;
    public static final HttpStatus PAYLOAD_TOO_LARGE = HttpStatus.PAYLOAD_TOO_LARGE;
    public static final HttpStatus URI_TOO_LONG = HttpStatus.URI_TOO_LONG;
    public static final HttpStatus UNSUPPORTED_MEDIA_TYPE = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    public static final HttpStatus REQUESTED_RANGE_NOT_SATISFIABLE = HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE;
    public static final HttpStatus EXPECTATION_FAILED = HttpStatus.EXPECTATION_FAILED;
    public static final HttpStatus I_AM_A_TEAPOT = HttpStatus.I_AM_A_TEAPOT;
    public static final HttpStatus UNPROCESSABLE_ENTITY = HttpStatus.UNPROCESSABLE_ENTITY;
    public static final HttpStatus LOCKED = HttpStatus.LOCKED;
    public static final HttpStatus FAILED_DEPENDENCY = HttpStatus.FAILED_DEPENDENCY;
    public static final HttpStatus TOO_EARLY = HttpStatus.TOO_EARLY;
    public static final HttpStatus UPGRADE_REQUIRED = HttpStatus.UPGRADE_REQUIRED;
    public static final HttpStatus PRECONDITION_REQUIRED = HttpStatus.PRECONDITION_REQUIRED;
    public static final HttpStatus TOO_MANY_REQUESTS = HttpStatus.TOO_MANY_REQUESTS;
    public static final HttpStatus REQUEST_HEADER_FIELDS_TOO_LARGE = HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE;
    public static final HttpStatus UNAVAILABLE_FOR_LEGAL_REASONS = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS;

    // 5xx Server Error
    public static final HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    public static final HttpStatus NOT_IMPLEMENTED = HttpStatus.NOT_IMPLEMENTED;
    public static final HttpStatus BAD_GATEWAY = HttpStatus.BAD_GATEWAY;
    public static final HttpStatus SERVICE_UNAVAILABLE = HttpStatus.SERVICE_UNAVAILABLE;
    public static final HttpStatus GATEWAY_TIMEOUT = HttpStatus.GATEWAY_TIMEOUT;
    public static final HttpStatus HTTP_VERSION_NOT_SUPPORTED = HttpStatus.HTTP_VERSION_NOT_SUPPORTED;
    public static final HttpStatus VARIANT_ALSO_NEGOTIATES = HttpStatus.VARIANT_ALSO_NEGOTIATES;
    public static final HttpStatus INSUFFICIENT_STORAGE = HttpStatus.INSUFFICIENT_STORAGE;
    public static final HttpStatus LOOP_DETECTED = HttpStatus.LOOP_DETECTED;
    public static final HttpStatus NOT_EXTENDED = HttpStatus.NOT_EXTENDED;
    public static final HttpStatus NETWORK_AUTHENTICATION_REQUIRED = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED;

    private HttpStatusCodes() {
        // Private constructor to prevent instantiation
    }
}