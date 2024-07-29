docker run -d  \
  --restart unless-stopped \
  --name mqtt \
  -p 1883:1883 \
  -p 9001:9001 \
  -v $(pwd)/mosquitto.conf:/mosquitto/config/mosquitto.conf \
  -v $(pwd)/mosquitto_passwd:/mosquitto/config/mosquitto_passwd \
  eclipse-mosquitto
