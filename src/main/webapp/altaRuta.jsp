<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.sanchez.app.rutas.models.*" %>

<%

    //Obtener lista de choferes
    List<Chofer> choferes = (List<Chofer>) request.getAttribute("choferes");
    List<Camion> camiones = (List<Camion>) request.getAttribute("camiones");

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
             integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
             crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<script src="//cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.27.0/moment-with-locales.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.css" integrity="sha512-bYPO5jmStZ9WI2602V2zaivdAnbAhtfzmxnEGh9RwtlI00I9s8ulGe4oBa5XxiC6tCITJH/QG70jswBhbLkxPw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>

</head>
<body>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" id="div1">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                                    <a class="navbar-brand" href="#" id="enlace1">Rutas App</a>
            </div>


            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Choferes<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/choferes/listar">Lista Choferes</a></li>
                            <li><a href="<%=request.getContextPath()%>/choferes/alta">Alta Chofer</a></li>

                        </ul>

                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Camiones<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/camiones/listar">Lista Camiones</a></li>
                            <li><a href="<%=request.getContextPath()%>/camiones/alta">Alta Camion</a></li>

                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Rutas<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/rutas/alta">Alta Ruta</a></li>

                        </ul>
                    </li>


                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>


    <div class="container body-content">

        <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyCWeeateTaYGqsHhNcmoDfT7Us-vLDZVPs&amp;sensor=false&amp;language=en"></script>



        <div class="container">

            <div class="row">

                <div class="col-md-12">

                    <h2>Iniciar Ruta</h2>

                </div>

                <div style="display: block;">

                    <input type="text" name="txtEsOD" id="txtEsOD">

                </div>


            </div>

            <div class="row">

                <!--Derecha-->
                <div class="col-md-6">

                    <div class="form-group">

                        <label for="">Chofer</label>

                        <select name="chofer" id="chofer" class="form-control">

                            <option value="">--seleccionar--</option>
                            <%
                                for(Chofer c: choferes){ %>
                                    <option value="<%=c.getId()%>"> <%=c.getNombre()%></option>
                            <% } %>

                        </select>

                    </div>

                    <div class="form-group">

                        <div class="row">

                            <div class="col-md-9">

                                <label for="">Origen</label>
                                <input type="text" name="origen" id="origen" class="form-control">
                                <input type="hidden" name="idOrigen" id="idOrigen" class="form-control">


                            </div>

                            <div class="col-md-3">

                                <button class="btn btn-primary btn-xs" style="margin-top: 30px;" onClick="getDireccion(1)">Obtener Datos</button>

                            </div>

                        </div>

                    </div>


                    <div class="form-group">

                        <label for="">Fecha Salida</label>
                        <input type="text" name="FSalida" id="FSalida" class="form-control">

                    </div>

                    <div class="form-group">

                        <label for="">Distancia</label>
                        <input type="text" name="distancia" id="distancia" class="from-control">

                    </div>

                </div>
                <!--Derecha-->

                <div class="col-md-6">

                                    <div class="form-group">

                                        <label for="">Camion</label>

                                        <select name="camion" id="camion" class="form-control">

                                            <option value="">--seleccionar--</option>
                                            <%
                                                for(Camion c: camiones){ %>
                                                    <option value="<%=c.getId()%>"> <%=c.getMatricula()%></option>
                                            <% } %>

                                        </select>

                                    </div>

                                    <div class="form-group">

                                        <div class="row">

                                            <div class="col-md-9">

                                                <label for="">Destino</label>
                                                <input type="text" name="destino" id="destino" class="form-control">
                                                <input type="hidden" name="idDestino" id="idDestino" class="form-control">


                                            </div>

                                            <div class="col-md-3">

                                                <button class="btn btn-primary btn-xs" style="margin-top: 30px;" onClick="getDireccion(2)">Obtener Datos</button>

                                            </div>

                                        </div>

                                    </div>


                                    <div class="form-group">

                                        <label for="">Fecha Estimada de Llegada</label>
                                        <input type="text" name="FELlegada" id="FELlegada" class="form-control">

                                    </div>


                                </div>



            </div>


            <div class="row">

                <div class="col-md-4">

                    <div class="form-group">

                        <labe>Capacidad Camion</labe>
                        <input type="text" name="capCamion" id="capCamion" class="form-control">


                    </div>

                </div>

                <div class="col-md-4 col-md-offset-4">

                    <div class="form-group">
                        <label>Carga Total</label>
                        <input type="text" name="cargaTotal" id="cargaTotal" class="form-control">


                    </div>

                    <!-- este es un comentario-->

                </div>

</div>



        </div>

    </div>

    <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="row">
                            <div class="col-md-12">
                                <h4>Guardar Dirección</h4>
                            </div>
                        </div>
                    </div>


                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="Calle">Calle</label>
                                    <input type="text" id="Calle" name="Calle" class="form-control">
                                </div>


                                <div class="form-group">
                                    <label for="Numero">Numero</label>
                                    <input type="text" id="Numero" name="Numero" class="form-control">
                                </div>


                                <div class="form-group">
                                    <label for="Colonia">Colonia</label>
                                    <input type="text" id="Colonia" name="Colonia" class="form-control"/>
                                </div>


                                <div class="form-group">
                                    <label for="Ciudad">Ciudad</label>
                                    <input type="text" id="Ciudad" name="Ciudad" class="form-control"/>
                                </div>


                                <div class="form-group">
                                    <label for="Estado">Estado</label>
                                    <input type="text" id="Estado" name="Estado" class="form-control"/>
                                </div>


                                <div class="form-group">
                                    <label for="CP">CP</label>
                                    <input type="text" id="CP" name="CP" class="form-control"/>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <div class="col-md-4">
                                    <button id="btnGuardarDir" class="btn btn-success" OnClick="btnGuardarDir()">Guardar</button>


                                </div>


                                <div class="col-md-4 col-md-offset-4">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>


                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>


      <script>

$(document).ready(function() {
   $.datetimepicker.setLocale('es');
      $("#FSalida").datetimepicker({format: 'd/m/Y H:i'});

           $("#FELlegada").
            datetimepicker({
                     format: 'd/m/Y H:i'
                    });

           $("FSalida").change(function() {
               CalcularDistancia();
    });
});

              function CalcularDistancia(){
        var Origen = $("#origen").val();
        var Destino = $("#destino").val();
        var Salida = $("#salida").val();
        if((Origen != "")&&(Destino != "")&&(Salida != "")){
            var directionService = new google.maps.DirectionsService;

            var solicitud = {
                origin: Origen,
                destination: Destino,
                travelMode: google.maps.TravelMode.DRIVING
            };
            directionService.route(solicitud, function (results, status){
                console.log(results);
                if(status == google.maps.DirectionsStatus.OK){
                    var Duracion = results.routes[0].legs[0].duration.value;
                    var Distancia = results.routes[0].legs[0].distance.value;
                    $("#distancia").val((Distancia / 1000).toFixed(2));

                    var partSalida = Salida.split(" ");
                    var partDate = partSalida[0].split("/");
                    var partTime = partSalida[1].split(":");
                    var FechaSalida = new Date(partDate[2], partDate[1] -1, partDate[0], partTime[0], partTime[1]);
                    var FechaEstimadaLlegada = new Date(FechaSalida.getTime()+Duracion*1000);
                    $("#FELlegada").val(moment(FechaEstimadaLlegada).format("DD/MM/YYYY HH:mm"));
                }else{
                    swal("Error de Google", "Google no obtuvo datos", "warning");
                }
            });
        }else{
            swal("Datos insuficientes", "Es imposible calcular tiempo y distancia", "warning");
        }
    }


      function btnGuardarDir() {

                    console.log("si jalo");
                  var calle = $("#Calle").val();
                  var numero= $("#Numero").val();
                  var colonia = $("#Colonia").val();
                  var ciudad = $("#Ciudad").val();
                  var estado = $("#Estado").val();
                  var cp = $("#CP").val();

                  var urlDestino = "/Gen8-Rutas/api/direcciones"

                  $.ajax({
                      type: 'POST',
                      url: urlDestino,
                      data: {"calle": calle, "numero": numero, "colonia": colonia, "ciudad": ciudad, "estado": estado, "cp": cp},

                      success: function(resp) {

                          console.log(resp);
                          $('myModal').modal('hide');
                          if ($("#txtEsOD").val() == 1) {

                              $("#idOrigen").val(resp.message);

                          }else{

                              $("#idDestino").val(resp.message);

                          }

                          swal("Exito", "Direccion almacenada correctamente", "success");

                      }
                  })

              }


             function LimpiarDatos() {
                 $("#Calle").val("");
                 $("#Numero").val("");
                 $("#Colonia").val("");
                 $("#Ciudad").val("");
                 $("#Estado").val("");
                 $("#CP").val("");

             }

             function getDireccion(fuente){
                 LimpiarDatos();
                 $('#myModal').modal('show');
                 var direccion = "";
                 if(fuente == 1){
                     direccion = $('#origen').val();
                 }
                 else{
                     direccion = $('#destino').val();
                 }
                 $("#txtEsOD").val(fuente);
                 if (direccion != "") {
                     var geocoder = new google.maps.Geocoder();
                     geocoder.geocode({'address':direccion},function(results, status){
                         console.log(results);
                         console.log(status);
                         if (status == google.maps.GeocoderStatus.OK) {
                             var numresults = results[0].address_components.length;
                             for (var indice = 0; indice < numresults; indice++) {
                                 var numresultstypes = results[0].address_components[indice].types.length;
                                 for (let propiedad = 0; propiedad < numresultstypes; propiedad++) {
                                     var Tipo = results[0].address_components[indice].types[propiedad];
                                     var Descripcion = results[0].address_components[indice].long_name;
                                     switch (Tipo) {
                                         case "route":
                                             $("#Calle").val(Descripcion);
                                             break;
                                         case "street_number":
                                             $("#Numero").val(Descripcion);
                                             break;
                                         case "sublocality_level_1":
                                             $("#Colonia").val(Descripcion);
                                             break;
                                         case "locality":
                                             $("#Ciudad").val(Descripcion);
                                             break;
                                         case "administrative_area_level_1":
                                             $("#Estado").val(Descripcion);
                                             break;
                                         case "postal_code":
                                             $("#CP").val(Descripcion);
                                             break;

                                         default:
                                             break;
                                     }

                                 }
                             }
                             if (results[0].address_components.length > 0) {
                                 if (fuente == 1 ) {
                                     $("#origen").val(results[0].formatted_address);
                                 }
                                 else{
                                     $("#destino").val(results[0].formatted_address);
                                 }
                             }
                         }
                         else{
                             swal("Error de Google","Google no obtuvo datos","warning");
                         }
                     });
                 }
                 else{
                     swal({
                         title:"¿Estas Seguro?",
                         text: "No podemos obtener datos sino proporciona una direccion",
                         type:"warning",
                         showCancelButton:true,
                         confirmButtonClass:"btn-warning",
                         confirmButtonText:"Cancelar",
                         closeOnConfirm: true,
                         closeOnCancel:true,
                     },
                     function(isConfirm) {
                         if (!isConfirm) {
                             $('#myModal').modal('hide');

                         }
                     });
                 }
             }

         </script>

</body>
</html>