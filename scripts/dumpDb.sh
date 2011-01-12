#!/bin/bash

/usr/local/mysql/bin/mysqldump --user='backup' --host=127.0.0.1 --single-transaction -q -Q -R --triggers josmud > sql/db_dump.sql

