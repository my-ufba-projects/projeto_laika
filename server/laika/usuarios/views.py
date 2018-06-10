from django.shortcuts import render
from .models import Perfil
from django.contrib.auth.models import User
from rest_framework import permissions,generics
from .permissions import PermissaoUsuario,PermissaoPerfil
from .Serializers import UserSerializer,PerfilSerializer,UserSerializer2

# Create your views here.

class InserirUsuario (generics.CreateAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer


class AtualizarUsuario (generics.UpdateAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,PermissaoUsuario)
    queryset = User.objects.all()
    serializer_class = UserSerializer

class ListasPerfil (generics.ListCreateAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,PermissaoPerfil)
    queryset = Perfil.objects.all()
    serializer_class = UserSerializer2

    def perform_create(self, serializer):
        serializer.save(usuario=self.request.user)

class DetalhePerfil (generics.RetrieveUpdateDestroyAPIView):
    permissions_classes =(permissions.IsAuthenticatedOrReadOnly,PermissaoPerfil)
    queryset = Perfil.objects.all()
    serializer_class = PerfilSerializer

