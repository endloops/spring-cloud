package sxs.xas.bqq.hqz.yjgc.myw.conf.webservice;

public class DemoServiceProxy implements sxs.xas.bqq.hqz.yjgc.myw.conf.webservice.DemoService {
  private String _endpoint = null;
  private sxs.xas.bqq.hqz.yjgc.myw.conf.webservice.DemoService demoService = null;
  
  public DemoServiceProxy() {
    _initDemoServiceProxy();
  }
  
  public DemoServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initDemoServiceProxy();
  }
  
  private void _initDemoServiceProxy() {
    try {
      demoService = (new sxs.xas.bqq.hqz.yjgc.myw.conf.webservice.impl.DemoServiceImplServiceLocator()).getDemoServiceImplPort();
      if (demoService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)demoService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)demoService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (demoService != null)
      ((javax.xml.rpc.Stub)demoService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public sxs.xas.bqq.hqz.yjgc.myw.conf.webservice.DemoService getDemoService() {
    if (demoService == null)
      _initDemoServiceProxy();
    return demoService;
  }
  
  public java.lang.String sayHello(java.lang.String arg0) throws java.rmi.RemoteException{
    if (demoService == null)
      _initDemoServiceProxy();
    return demoService.sayHello(arg0);
  }
  
  
}