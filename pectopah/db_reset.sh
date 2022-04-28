#!/usr/bin/env bash

docker-compose down
docker volume rm pectopah_pdb_data
docker-compose up -d
