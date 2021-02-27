FROM nginx:1.19-alpine

COPY server.crt /etc/nginx/cert/server.crt
COPY server.key /etc/nginx/cert/server.key
COPY server.pass /etc/nginx/cert/server.pass
#COPY root_ca.pem /etc/nginx/cert/root_ca.pem

COPY routes/default.conf /etc/nginx/templates/default.conf.template
#COPY routes/api_gateway_api_core.conf /etc/nginx/include/api_gateway_api_core.conf
#COPY routes/api_gateway_api_layout_manager.conf /etc/nginx/include/api_gateway_api_layout_manager.conf
#COPY routes/api_gateway_ext_api_pim.conf /etc/nginx/include/api_gateway_ext_api_pim.conf

# Заглушки для отладки
#COPY stub /usr/share/nginx/html/stub
