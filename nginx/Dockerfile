FROM nginx:1.19-alpine

COPY server.crt /etc/nginx/cert/server.crt
COPY server.key /etc/nginx/cert/server.key
COPY server.pass /etc/nginx/cert/server.pass
COPY ca.crt /etc/nginx/cert/ca.crt

COPY routes/default.conf /etc/nginx/templates/default.conf.template
#COPY routes/api_gateway_api_core.conf /etc/nginx/include/api_gateway_api_core.conf
#COPY routes/api_gateway_api_layout_manager.conf /etc/nginx/include/api_gateway_api_layout_manager.conf
#COPY routes/api_gateway_ext_api_pim.conf /etc/nginx/include/api_gateway_ext_api_pim.conf

# Заглушки для отладки
#COPY stub /usr/share/nginx/html/stub
