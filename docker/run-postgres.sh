docker run  -d --name iot-postgres \
    -e  POSTGRES_USER=postgres \
    -e  POSTGRES_PASSWORD=postgres \
    -e  POSTGRES_DB=iot  \
    -v  postgres_data:/var/lib/postgresql/data \
    -p  5432:5432 \
    postgres:15.2-alpine