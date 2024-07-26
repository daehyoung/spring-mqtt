docker run --name nginx  -d --rm  -p 8080:80 -v $(pwd)/nginx.conf:/etc/nginx/nginx.conf:ro nginx

