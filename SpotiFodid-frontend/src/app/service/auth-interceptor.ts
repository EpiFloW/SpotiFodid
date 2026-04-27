import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {inject} from "@angular/core";
import {Auth} from "./auth";
import {tap} from "rxjs";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(Auth);

  const authReq = req.clone({
    withCredentials: true
  });

  return next(authReq).pipe(
    tap({
      error: (err: HttpErrorResponse) => {
        if (err.status === 401) {
          if (err.url?.includes('api/get-authenticated-user')) {
          } else {
             authService.openOrCloseAuthPopup("OPEN");
          }
        }
      }
    })
  );
};