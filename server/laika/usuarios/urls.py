from django.urls import path
from rest_framework.urlpatterns import format_suffix_patterns
from . import views

urlpatterns = [
    #path('usuarios/', views.ListasUsuarios.as_view()),
    path('usuarios/inserir', views.InserirUsuario.as_view()),
    path('usuarios/atualizar/<int:pk>', views.AtualizarUsuario.as_view()),
    path('perfil', views.ListasPerfil.as_view()),
    path('perfil/<int:pk>', views.DetalhePerfil.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)