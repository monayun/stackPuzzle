FROM mysql:8.0

COPY init.sql /docker-entrypoint-initdb.d

ENV MYSQL_DATABASE="books"
ENV MYSQL_USER="root"
ENV MYSQL_ROOT_PASSWORD="1234"
ENV MYSQL_PASSWORD="1234"
ENV MYSQL_HOST="%"

CMD ["--character-set-server=utf8mb4","--collation-server=utf8mb4_unicode_ci"]