
#!/usr/bin/env bash
# Local environment variables for EcoHub backend
# WARNING: This file contains sensitive credentials. Keep it local and do NOT commit.

# Original psql connection string (provided):
# psql 'postgresql://neondb_owner:npg_rtiNm1L2Ceap@ep-wild-wave-a4tm5cef-pooler.us-east-1.aws.neon.tech/neondb?sslmode=require&channel_binding=require'

# JDBC URL (Spring Boot expects jdbc:postgresql://...)
export SPRING_DATASOURCE_URL="jdbc:postgresql://ep-wild-wave-a4tm5cef-pooler.us-east-1.aws.neon.tech:5432/neondb?sslmode=require&channel_binding=require"

# Database credentials
export SPRING_DATASOURCE_USERNAME="neondb_owner"
export SPRING_DATASOURCE_PASSWORD="npg_rtiNm1L2Ceap"

# Optional: override server port
# export SERVER_PORT=8080

# Usage:
# 1) Ensure this file is NOT committed (it's in .gitignore).
# 2) Load it in the current shell before running the app:
#    source env.sh
# 3) Start the backend:
#    ./mvnw spring-boot:run

