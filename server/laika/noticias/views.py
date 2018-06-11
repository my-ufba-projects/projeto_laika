from django.shortcuts import render
from .models import News
from .serializer import NewsSerializer
from rest_framework import permissions
from .permissions import IsOwnerOrReadOnly
from rest_framework import generics

# Create your views here.
class NewsList(generics.ListCreateAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,IsOwnerOrReadOnly)
    queryset = News.objects.all()
    serializer_class = NewsSerializer

    def perform_create(self, serializer):
        serializer.save(criador=self.request.user)

class NewsDetail(generics.RetrieveUpdateDestroyAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,IsOwnerOrReadOnly)
    queryset = News.objects.all()
    serializer_class = NewsSerializer

