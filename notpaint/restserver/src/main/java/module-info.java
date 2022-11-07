module notpaint.restserver {
    requires notpaint.core;
    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    opens notpaint.restserver to spring.beans, spring.context, spring.web;
}
